package br.com.projeto.miniautorizador.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
public class TransacaoRequestDTO {
    @NotNull
    private String numeroCartao;
    @NotNull
    private String senha;
    @NotNull
    private BigDecimal valor;
}
