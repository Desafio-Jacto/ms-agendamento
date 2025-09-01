package br.com.jacto.api_agendamento.peca.service;

import br.com.jacto.api_agendamento.agendamento.model.Agendamento;
import br.com.jacto.api_agendamento.infra.exceptions.RegraNegocioException;
import br.com.jacto.api_agendamento.peca.dto.request.PecaUsadaRequestDTO;
import br.com.jacto.api_agendamento.peca.model.PecaUsada;
import br.com.jacto.api_agendamento.peca.repository.IPecaUsadaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PecaUsadaService {

    private final IPecaUsadaRepository pecaUsadaRepository;
    private final RestTemplate restTemplate;

    @Value("${api.security.api-key}")
    private String apiKey;

    public PecaUsadaService(IPecaUsadaRepository pecaUsadaRepository,  RestTemplate restTemplate) {
        this.pecaUsadaRepository = pecaUsadaRepository;
        this.restTemplate = restTemplate;
    }

    public void salvarPecaUsada(Agendamento agendamento, PecaUsadaRequestDTO pecaUsadaDTO) {
        PecaUsada pecaUsada = new PecaUsada();
        pecaUsada.setAgendamento(agendamento);
        pecaUsada.setIdPeca(pecaUsadaDTO.getIdPeca());
        pecaUsada.setQuantidade(pecaUsadaDTO.getQuantidade());
        pecaUsadaRepository.save(pecaUsada);
    }

    public void verificarQuantidadePeca(PecaUsadaRequestDTO pecaUsadaDTO) {
        String url = "http://localhost:8090/peca/quantidade/" + pecaUsadaDTO.getIdPeca();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Integer> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    Integer.class
            );

            Integer quantidadeDisponivel = response.getBody();
            if (quantidadeDisponivel == null || quantidadeDisponivel < pecaUsadaDTO.getQuantidade()) {
                throw new RegraNegocioException("Quantidade de peça insuficiente.");
            }

        } catch (RegraNegocioException e) {
            throw e;
        } catch (Exception e) {
            throw new RegraNegocioException(
                    "Erro ao verificar a quantidade da peça '" + pecaUsadaDTO.getIdPeca() + "'."
            );
        }
    }

}
