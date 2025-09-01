package br.com.jacto.api_agendamento.avaliacao.controller;

import br.com.jacto.api_agendamento.avaliacao.dto.request.AvaliacaoRequestDto;
import br.com.jacto.api_agendamento.avaliacao.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
@Tag(name = "Avaliação", description = "Operações relacionadas a avaliações de agendamentos")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @Operation(summary = "Avalia um agendamento", description = "Registra uma avaliação para um agendamento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação registrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PostMapping("/{idAgendamento}/avaliar")
    public ResponseEntity<String> avaliarAgendamento(
            @PathVariable Integer idAgendamento,
            @RequestBody AvaliacaoRequestDto avaliacaoDto) {

        avaliacaoService.avaliarAgendamento(idAgendamento, avaliacaoDto);
        return ResponseEntity.ok("Avaliação registrada com sucesso.");
    }

    @Operation(summary = "Edita uma avaliação", description = "Atualiza uma avaliação existente com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    @PutMapping("/avaliacoes/{idAvaliacao}")
    public ResponseEntity<String> editarAvaliacao(
            @PathVariable Integer idAvaliacao,
            @RequestBody AvaliacaoRequestDto avaliacaoDto) {

        avaliacaoService.editarAvaliacao(idAvaliacao, avaliacaoDto);
        return ResponseEntity.ok("Avaliação atualizada com sucesso.");
    }
}
