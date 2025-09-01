package br.com.jacto.api_agendamento.infra.exceptions;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String message) {
        super(message);
    }
}
