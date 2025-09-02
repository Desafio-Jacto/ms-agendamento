package br.com.jacto.api_agendamento.equipamento.service;

import br.com.jacto.api_agendamento.equipamento.dto.request.EquipamentoUsadoRequestDto;
import br.com.jacto.api_agendamento.equipamento.model.EquipamentoUsado;
import br.com.jacto.api_agendamento.equipamento.repository.IEquipamentoUsadoRepository;
import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipamentoUsadoServiceTest {

    private IEquipamentoUsadoRepository equipamentoUsadoRepository;
    private EquipamentoUsadoService equipamentoUsadoService;

    @BeforeEach
    void setUp() {
        equipamentoUsadoRepository = mock(IEquipamentoUsadoRepository.class);
        equipamentoUsadoService = new EquipamentoUsadoService(equipamentoUsadoRepository);
    }

    @Test
    void deveSalvarEquipamentoUsadoComSucesso() {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdAgendamento(1);

        EquipamentoUsadoRequestDto dto = new EquipamentoUsadoRequestDto();
        dto.setIdEquipamento(10);
        dto.setQuantidade(3);

        ArgumentCaptor<EquipamentoUsado> captor = ArgumentCaptor.forClass(EquipamentoUsado.class);

        equipamentoUsadoService.salvarEquipamentoUsado(agendamento, dto);

        verify(equipamentoUsadoRepository, times(1)).save(captor.capture());
        EquipamentoUsado salvo = captor.getValue();

        assertEquals(agendamento, salvo.getAgendamento());
        assertEquals(10, salvo.getIdEquipamento());
        assertEquals(3, salvo.getQuantidade());
    }
}
