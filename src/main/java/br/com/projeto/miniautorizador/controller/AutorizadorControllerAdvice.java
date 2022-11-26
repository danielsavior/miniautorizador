package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.ErrorDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AutorizadorControllerAdvice {

    @ExceptionHandler({AutorizadorException.class})
    public ResponseEntity<ErrorDTO> handlerAutorizadorException(AutorizadorException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().codigo(ex.getStatus().toString()).motivo(ex.getDetail()).build(), ex.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().codigo(HttpStatus.BAD_REQUEST.toString()).motivo(ex.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
