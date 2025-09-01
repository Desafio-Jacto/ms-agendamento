package br.com.jacto.api_agendamento.infra.exception_handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class MensagemErroResponse {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public MensagemErroResponse(HttpStatus status, String message, LocalDateTime timestamp, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
