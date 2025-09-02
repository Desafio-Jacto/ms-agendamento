package br.com.jacto.api_agendamento.agendamento.controller;

import br.com.jacto.api_agendamento.agendamento.dto.request.AgendamentoRequestDto;
import br.com.jacto.api_agendamento.agendamento.dto.request.TecnicoMetricasDto;
import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.agendamento.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendamentoControllerTest {

    @Mock
    private AgendamentoService service;

    @InjectMocks
    private AgendamentoController controller;

    private AgendamentoResponseDto agendamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        agendamento = new AgendamentoResponseDto();
        agendamento.setIdAgendamento(1);
        agendamento.setObservacoes("Teste");
    }

    @Test
    void deveListarTodos() {
        when(service.listarTodos()).thenReturn(Arrays.asList(agendamento));

        ResponseEntity<List<AgendamentoResponseDto>> response = controller.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void deveBuscarPorId() {
        when(service.buscarPorId(1)).thenReturn(agendamento);

        ResponseEntity<AgendamentoResponseDto> response = controller.buscarPorId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Teste", response.getBody().getObservacoes());
        verify(service, times(1)).buscarPorId(1);
    }

    @Test
    void deveCriarAgendamento() {
        AgendamentoRequestDto request = new AgendamentoRequestDto();
        when(service.criar(request)).thenReturn(agendamento);

        ResponseEntity<AgendamentoResponseDto> response = controller.criar(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getIdAgendamento());
        verify(service, times(1)).criar(request);
    }

    @Test
    void deveAtualizarAgendamento() {
        AgendamentoRequestDto request = new AgendamentoRequestDto();
        when(service.atualizar(1, request)).thenReturn(agendamento);

        ResponseEntity<AgendamentoResponseDto> response = controller.atualizar(1, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getIdAgendamento());
        verify(service, times(1)).atualizar(1, request);
    }

    @Test
    void deveDeletarAgendamento() {
        doNothing().when(service).deletar(1);

        ResponseEntity<Void> response = controller.deletar(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deletar(1);
    }

    @Test
    void deveFinalizarAgendamento() {
        doNothing().when(service).finalizarAgendamento(1);

        ResponseEntity<String> response = controller.finalizarAgendamento(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Agendamento finalizado com sucesso.", response.getBody());
        verify(service, times(1)).finalizarAgendamento(1);
    }

    @Test
    void deveListarMetricasUsuario() {
        TecnicoMetricasDto metricas = new TecnicoMetricasDto();
        when(service.obterMetricasUsuario(10)).thenReturn(metricas);

        ResponseEntity<TecnicoMetricasDto> response = controller.listarMetricasPeloIdUsuario(10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(service, times(1)).obterMetricasUsuario(10);
    }

    @Test
    void deveGerarRelatorio() throws Exception {
        byte[] pdf = new byte[]{1, 2, 3};
        when(service.gerarRelatorio(1)).thenReturn(pdf);

        ResponseEntity<byte[]> response = controller.gerarRelatorio(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(pdf, response.getBody());
        verify(service, times(1)).gerarRelatorio(1);
    }

    @Test
    void deveRetornarNotFoundQuandoRelatorioNaoExiste() throws Exception {
        when(service.gerarRelatorio(1)).thenReturn(null);

        ResponseEntity<byte[]> response = controller.gerarRelatorio(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(service, times(1)).gerarRelatorio(1);
    }
}
