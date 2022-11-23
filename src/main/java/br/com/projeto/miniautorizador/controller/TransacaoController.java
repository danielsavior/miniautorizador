package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.service.TransacaoServiceBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacaoController {

    @PostMapping(value = "/transacoes", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Efetuar transacao",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Transação realizada."),
                    @ApiResponse(responseCode = "422", description = "Transação não realizada.")
            }
    )
    public ResponseEntity<Void> transacoes(@RequestBody TransacaoRequestDTO dto) {
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
