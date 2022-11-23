package br.com.projeto.miniautorizador.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class CartaoRequestDTO {
    @NotNull
    private String numeroCartao;
    @NotNull
    private String senha;
}
