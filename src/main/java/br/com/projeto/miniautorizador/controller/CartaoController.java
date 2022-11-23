package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartaoController {

    @PostMapping(value = "/cartoes", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Criar cartao",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cartao criado."),
                    @ApiResponse(responseCode = "422", description = "Cartao ja existia.")
            }
    )
    public ResponseEntity<CartaoResponseDTO> cartoes(@RequestBody CartaoRequestDTO dto) {
        return ResponseEntity.accepted().body(CartaoResponseDTO.builder().build());
    }

    @GetMapping(value = "/cartoes/{numeroCartao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Consultar cartao",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cartao encontrado."),
                    @ApiResponse(responseCode = "404", description = "Cartao inexistente.")
            }
    )
    public ResponseEntity<CartaoResponseDTO> cartoes(@PathVariable String numeroCartao) {
        return ResponseEntity.ok(CartaoResponseDTO.builder().build());
    }
}
