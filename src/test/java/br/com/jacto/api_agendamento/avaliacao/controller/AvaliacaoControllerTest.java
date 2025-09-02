package br.com.jacto.api_agendamento.avaliacao.controller;

import br.com.jacto.api_agendamento.avaliacao.dto.request.AvaliacaoRequestDto;
import br.com.jacto.api_agendamento.avaliacao.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoControllerTest {

    @Mock
    private AvaliacaoService avaliacaoService;

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    private AvaliacaoRequestDto avaliacaoRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        avaliacaoRequestDto = new AvaliacaoRequestDto();
    }

    @Test
    void deveAvaliarAgendamentoComSucesso() {
        doNothing().when(avaliacaoService).avaliarAgendamento(1, avaliacaoRequestDto);

        ResponseEntity<String> response = avaliacaoController.avaliarAgendamento(1, avaliacaoRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Avaliação registrada com sucesso.", response.getBody());
        verify(avaliacaoService, times(1)).avaliarAgendamento(1, avaliacaoRequestDto);
    }

    @Test
    void deveEditarAvaliacaoComSucesso() {
        doNothing().when(avaliacaoService).editarAvaliacao(10, avaliacaoRequestDto);

        ResponseEntity<String> response = avaliacaoController.editarAvaliacao(10, avaliacaoRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Avaliação atualizada com sucesso.", response.getBody());
        verify(avaliacaoService, times(1)).editarAvaliacao(10, avaliacaoRequestDto);
    }
}
