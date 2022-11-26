package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import br.com.projeto.miniautorizador.dto.SaldoCartaoResponseDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;
    @PostMapping(value = "/cartoes", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Criar cartao",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cartao criado."),
                    @ApiResponse(responseCode = "422", description = "Cartao ja existia.")
            }
    )
    public ResponseEntity<CartaoResponseDTO> cartoes(@Valid @RequestBody CartaoRequestDTO dto) throws AutorizadorException {
        CartaoResponseDTO responseDTO = cartaoService.criarCartao(dto);
        return new ResponseEntity<>(responseDTO, responseDTO.getCartaoJaExistia() ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.CREATED);
    }

    @GetMapping(value = "/cartoes/{numeroCartao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Consultar saldo do cartao",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cartao encontrado."),
                    @ApiResponse(responseCode = "404", description = "Cartao inexistente.")
            }
    )
    public ResponseEntity<SaldoCartaoResponseDTO> cartoes(@PathVariable String numeroCartao) throws AutorizadorException {
        return ResponseEntity.ok(cartaoService.consultarSaldoDoCartao(numeroCartao));
    }
}
