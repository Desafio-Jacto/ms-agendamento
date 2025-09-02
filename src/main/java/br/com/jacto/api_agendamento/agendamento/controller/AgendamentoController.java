package br.com.jacto.api_agendamento.agendamento.controller;

import br.com.jacto.api_agendamento.agendamento.dto.request.AgendamentoRequestDto;
import br.com.jacto.api_agendamento.agendamento.dto.request.TecnicoMetricasDto;
import br.com.jacto.api_agendamento.agendamento.dto.response.AgendamentoResponseDto;
import br.com.jacto.api_agendamento.agendamento.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@Tag(name = "Agendamento", description = "Operações relacionadas a agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os agendamentos", description = "Retorna uma lista de todos os agendamentos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    })
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Busca agendamento por ID", description = "Retorna um agendamento específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Cria um novo agendamento", description = "Cria um novo agendamento com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> criar(@RequestBody AgendamentoRequestDto dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @Operation(summary = "Atualiza um agendamento existente", description = "Atualiza os dados de um agendamento com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody AgendamentoRequestDto dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Deleta um agendamento", description = "Deleta um agendamento com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Finaliza um agendamento", description = "Marca um agendamento como finalizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento finalizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PatchMapping("/{idAgendamento}/finalizar")
    public ResponseEntity<String> finalizarAgendamento(@PathVariable Integer idAgendamento) {
        service.finalizarAgendamento(idAgendamento);
        return ResponseEntity.ok("Agendamento finalizado com sucesso.");
    }

    @Operation(summary = "Obtém métricas de usuário", description = "Retorna métricas de agendamentos e avaliações de um usuário com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Métricas obtidas com sucesso")
    })
    @GetMapping("/metricas/{idUsuario}")
    public ResponseEntity<TecnicoMetricasDto> listarMetricasPeloIdUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(service.obterMetricasUsuario(idUsuario));
    }

    @Operation(summary = "Gera um relatório PDF", description = "Gera e retorna um relatório em formato PDF para um agendamento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("/{id}/relatorio")
    public ResponseEntity<byte[]> gerarRelatorio(@PathVariable Integer id) throws Exception {
        byte[] pdf = service.gerarRelatorio(id);

        if (pdf == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("agendamento_" + id + ".pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

}
