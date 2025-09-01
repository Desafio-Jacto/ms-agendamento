package br.com.jacto.api_agendamento.avaliacao.service;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.agendamento.repository.IAgendamentoRepository;
import br.com.jacto.api_agendamento.avaliacao.dto.request.AvaliacaoRequestDto;
import br.com.jacto.api_agendamento.avaliacao.model.Avaliacao;
import br.com.jacto.api_agendamento.avaliacao.repository.IAvaliacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    private final IAgendamentoRepository agendamentoRepository;
    private final IAvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(IAgendamentoRepository agendamentoRepository,  IAvaliacaoRepository avaliacaoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public void avaliarAgendamento(Integer idAgendamento, AvaliacaoRequestDto avaliacaoDto) {
        Agendamento agendamento = agendamentoRepository.findById(idAgendamento)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNotaAvaliacao(avaliacaoDto.getNotaAvaliacao());
        novaAvaliacao.setComentario(avaliacaoDto.getComentario());
        novaAvaliacao.setAgendamento(agendamento);

        agendamento.getAvaliacoes().add(novaAvaliacao);
        agendamentoRepository.save(agendamento);
    }

    public void editarAvaliacao(Integer idAvaliacao, AvaliacaoRequestDto avaliacaoDto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));

        avaliacao.setNotaAvaliacao(avaliacaoDto.getNotaAvaliacao());
        avaliacao.setComentario(avaliacaoDto.getComentario());

        avaliacaoRepository.save(avaliacao);
    }
}
