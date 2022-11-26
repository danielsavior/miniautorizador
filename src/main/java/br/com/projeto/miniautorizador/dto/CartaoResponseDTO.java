package br.com.projeto.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CartaoResponseDTO {
    private String numeroCartao;
    private String senha;

    @Setter
    @JsonIgnore
    private Boolean cartaoJaExistia;
}
