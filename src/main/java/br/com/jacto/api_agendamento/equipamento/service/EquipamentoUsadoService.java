package br.com.jacto.api_agendamento.equipamento.service;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.equipamento.dto.request.EquipamentoUsadoRequestDTO;
import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import br.com.jacto.api_agendamento.equipamento.repository.IEquipamentoUsadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoUsadoService {

    private final IEquipamentoUsadoRepository equipamentoUsadoRepository;

    public EquipamentoUsadoService(IEquipamentoUsadoRepository equipamentoUsadoRepository) {
        this.equipamentoUsadoRepository = equipamentoUsadoRepository;
    }

    public void salvarEquipamentoUsado(Agendamento agendamento, EquipamentoUsadoRequestDTO equipamentoDto) {
        EquipamentoUsado equipamentoUsado = new EquipamentoUsado();
        equipamentoUsado.setAgendamento(agendamento);
        equipamentoUsado.setIdEquipamento(equipamentoDto.getIdEquipamento());
        equipamentoUsado.setQuantidade(equipamentoDto.getQuantidade());
        equipamentoUsadoRepository.save(equipamentoUsado);
    }

}
