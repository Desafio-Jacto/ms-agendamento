package br.com.jacto.api_agendamento.avaliacao.service;

import br.com.jacto.api_agendamento.agendamento.enums.AvaliacaoClienteEnum;
import br.com.jacto.api_agendamento.avaliacao.dto.request.AvaliacaoRequestDto;
import br.com.jacto.api_agendamento.avaliacao.model.Avaliacao;
import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.avaliacao.repository.IAvaliacaoRepository;
import br.com.jacto.api_agendamento.agendamento.repository.IAgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoServiceTest {

    @Mock
    private IAgendamentoRepository agendamentoRepository;

    @Mock
    private IAvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    private AvaliacaoRequestDto avaliacaoRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        avaliacaoRequestDto = new AvaliacaoRequestDto();
        avaliacaoRequestDto.setNotaAvaliacao(AvaliacaoClienteEnum.MUITO_BOM);
        avaliacaoRequestDto.setComentario("Excelente serviço!");
    }

    @Test
    void deveAvaliarAgendamentoComSucesso() {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdAgendamento(1);
        agendamento.setAvaliacoes(new HashSet<>());

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(agendamento));
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamento);

        avaliacaoService.avaliarAgendamento(1, avaliacaoRequestDto);

        assertEquals(1, agendamento.getAvaliacoes().size());

        Avaliacao avaliacao = agendamento.getAvaliacoes().iterator().next();
        assertEquals(AvaliacaoClienteEnum.MUITO_BOM, avaliacao.getNotaAvaliacao());
        assertEquals("Excelente serviço!", avaliacao.getComentario());
        assertEquals(agendamento, avaliacao.getAgendamento());

        verify(agendamentoRepository, times(1)).findById(1);
        verify(agendamentoRepository, times(1)).save(agendamento);
    }


    @Test
    void deveLancarExcecaoQuandoAgendamentoNaoEncontrado() {
        when(agendamentoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> avaliacaoService.avaliarAgendamento(99, avaliacaoRequestDto));

        assertEquals("Agendamento não encontrado.", exception.getMessage());
        verify(agendamentoRepository, times(1)).findById(99);
        verify(agendamentoRepository, never()).save(any());
    }

    @Test
    void deveEditarAvaliacaoComSucesso() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdAvaliacao(10);
        avaliacao.setNotaAvaliacao(AvaliacaoClienteEnum.RUIM);
        avaliacao.setComentario("Não gostei");

        when(avaliacaoRepository.findById(10)).thenReturn(Optional.of(avaliacao));
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);

        avaliacaoService.editarAvaliacao(10, avaliacaoRequestDto);

        assertEquals(AvaliacaoClienteEnum.MUITO_BOM, avaliacao.getNotaAvaliacao());
        assertEquals("Excelente serviço!", avaliacao.getComentario());

        verify(avaliacaoRepository, times(1)).findById(10);
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    @Test
    void deveLancarExcecaoQuandoAvaliacaoNaoEncontrada() {
        when(avaliacaoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> avaliacaoService.editarAvaliacao(99, avaliacaoRequestDto));

        assertEquals("Avaliação não encontrada.", exception.getMessage());
        verify(avaliacaoRepository, times(1)).findById(99);
        verify(avaliacaoRepository, never()).save(any());
    }
}
