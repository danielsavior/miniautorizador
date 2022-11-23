package br.com.projeto.miniautorizador.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartaoResponseDTO {
    private String numeroCartao;
    private String senha;
}
