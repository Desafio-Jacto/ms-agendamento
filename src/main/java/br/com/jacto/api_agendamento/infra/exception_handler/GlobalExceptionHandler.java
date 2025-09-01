package br.com.jacto.api_agendamento.infra.exception_handler;

import br.com.jacto.api_agendamento.infra.exceptions.RegraNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<MensagemErroResponse> handleParametroInvalidoException(
            RegraNegocioException ex,
            HttpServletRequest request) {

        MensagemErroResponse error = new MensagemErroResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
