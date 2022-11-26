package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping(value = "/transacoes", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Efetuar transacao",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Transação realizada."),
                    @ApiResponse(responseCode = "422", description = "Transação não realizada.")
            }
    )
    public ResponseEntity<Void> transacoes(@Valid @RequestBody TransacaoRequestDTO dto) throws AutorizadorException {
        transacaoService.efetuarTransacao(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
