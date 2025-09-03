package br.com.jacto.api_agendamento.infra.exceptions;

public class AgendamentoNotFoundException extends RuntimeException {
    public AgendamentoNotFoundException(String message) {
        super(message);
    }
}
