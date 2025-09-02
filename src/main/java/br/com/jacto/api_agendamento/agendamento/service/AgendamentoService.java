package br.com.jacto.api_agendamento.agendamento.service;

import br.com.jacto.api_agendamento.agendamento.dto.request.AgendamentoNotificacaoDto;
import br.com.jacto.api_agendamento.agendamento.dto.request.AgendamentoRequestDto;
import br.com.jacto.api_agendamento.agendamento.dto.request.TecnicoMetricasDto;
import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.agendamento.mapper.AgendamentoMapper;
import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.agendamento.repository.IAgendamentoRepository;
import br.com.jacto.api_agendamento.avaliacao.repository.IAvaliacaoRepository;
import br.com.jacto.api_agendamento.equipamento.dto.request.EquipamentoUsadoRequestDto;
import br.com.jacto.api_agendamento.equipamento.service.EquipamentoUsadoService;
import br.com.jacto.api_agendamento.infra.exceptions.RegraNegocioException;
import br.com.jacto.api_agendamento.infra.rabbitmq.RabbitConfig;
import br.com.jacto.api_agendamento.infra.security.SecurityUtils;
import br.com.jacto.api_agendamento.peca.dto.request.PecaUsadaRequestDto;
import br.com.jacto.api_agendamento.peca.service.PecaUsadaService;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final IAgendamentoRepository agendamentoRepository;
    private final EquipamentoUsadoService equipamentoUsadoService;
    private final PecaUsadaService pecaUsadaService;
    private final AgendamentoMapper mapper;
    private final RabbitTemplate rabbitTemplate;
    private final IAvaliacaoRepository avaliacaoRepository;

    public AgendamentoService(IAgendamentoRepository agendamentoRepository, EquipamentoUsadoService equipamentoUsadoService,
                              PecaUsadaService pecaUsadaService, AgendamentoMapper mapper,  RabbitTemplate rabbitTemplate,
                              IAvaliacaoRepository avaliacaoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.mapper = mapper;
        this.equipamentoUsadoService = equipamentoUsadoService;
        this.pecaUsadaService = pecaUsadaService;
        this.rabbitTemplate = rabbitTemplate;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public List<AgendamentoResponseDto> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public AgendamentoResponseDto buscarPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    @Transactional
    public AgendamentoResponseDto criar(AgendamentoRequestDto dto) {
        if (dto.getDataAgendamento().isBefore(LocalDateTime.now())) {
            throw new RegraNegocioException("Não é possível agendar para datas anteriores");
        }

        if (dto.getPecasUsadas() != null) {
            for (PecaUsadaRequestDto pecaDto : dto.getPecasUsadas()) {
                pecaUsadaService.verificarQuantidadePeca(pecaDto);
            }
        }

        Agendamento agendamento = mapper.toEntity(dto);

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        if (dto.getEquipamentosUsados() != null) {
            for (EquipamentoUsadoRequestDto equipamentoDto : dto.getEquipamentosUsados()) {
                equipamentoUsadoService.salvarEquipamentoUsado(agendamentoSalvo, equipamentoDto);
            }
        }

        if (dto.getPecasUsadas() != null) {
            for (PecaUsadaRequestDto pecaDto : dto.getPecasUsadas()) {
                pecaUsadaService.salvarPecaUsada(agendamentoSalvo, pecaDto);
            }
        }

        enviarNotificacao(agendamentoSalvo);

        return mapper.toDto(agendamentoSalvo);
    }

    public AgendamentoResponseDto atualizar(Integer id, AgendamentoRequestDto dto) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    Agendamento atualizado = mapper.toEntity(dto);
                    atualizado.setIdAgendamento(agendamento.getIdAgendamento());
                    return mapper.toDto(agendamentoRepository.save(atualizado));
                })
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void deletar(Integer id) {
        agendamentoRepository.deleteById(id);
    }

    @Transactional
    public void finalizarAgendamento(Integer idAgendamento) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));
        agendamento.setAgendamentoFinalizado(true);
        agendamentoRepository.save(agendamento);
    }

    private void enviarNotificacao(Agendamento agendamento) {
        AgendamentoNotificacaoDto notificacaoDto = new AgendamentoNotificacaoDto();
        notificacaoDto.setIdAgendamento(agendamento.getIdAgendamento());
        notificacaoDto.setDataAgendamento(agendamento.getDataAgendamento());
        notificacaoDto.setEmail(SecurityUtils.getEmailUsuarioLogado());


        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                notificacaoDto
        );
    }

    public TecnicoMetricasDto obterMetricasUsuario(Integer idUsuario) {
        Integer totalAgendamentos = agendamentoRepository.countByIdUsuario(idUsuario);
        Integer agendamentosConcluidos = agendamentoRepository.countByIdUsuarioAndAgendamentoFinalizadoTrue(idUsuario);
        Integer totalAvaliados = avaliacaoRepository.countByAgendamentoIdUsuario(idUsuario);

        return new TecnicoMetricasDto(
                totalAgendamentos,
                agendamentosConcluidos,
                totalAvaliados
        );
    }

    public byte[] gerarRelatorio(Integer id) throws Exception {
        AgendamentoResponseDto agendamento = buscarPorId(id);

        if (agendamento == null) {
            return null;
        }

        String equipamentos = agendamento.getEquipamentosUsados().stream()
                .map(e -> "ID: " + e.getIdEquipamento() + " Quantidade: " + e.getQuantidade())
                .collect(Collectors.joining(", "));

        String pecas = agendamento.getPecasUsadas().stream()
                .map(p -> "ID: " + p.getIdPeca() + " Quantidade: " + p.getQuantidadeUsada())
                .collect(Collectors.joining(", "));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idAgendamento", agendamento.getIdAgendamento());
        parametros.put("dataAgendamento", agendamento.getDataAgendamento());
        parametros.put("tipoAgendamento", agendamento.getTipoAgendamento());
        parametros.put("observacoes", agendamento.getObservacoes());
        parametros.put("prioridade", agendamento.getPrioridade().name());
        parametros.put("agendamentoFinalizado", agendamento.getAgendamentoFinalizado());
        parametros.put("avaliacaoCliente", agendamento.getAvaliacaoCliente());
        parametros.put("idUsuario", agendamento.getIdUsuario());
        parametros.put("idFazenda", agendamento.getIdFazenda());
        parametros.put("equipamentosUsados", equipamentos);
        parametros.put("pecasUsadas", pecas);

        InputStream reportStream = getClass().getResourceAsStream("/reports/agendamento.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
