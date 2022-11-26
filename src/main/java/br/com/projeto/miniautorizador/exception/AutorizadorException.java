package br.com.projeto.miniautorizador.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class AutorizadorException extends RuntimeException{
    private HttpStatus status;
    private String detail;
}
