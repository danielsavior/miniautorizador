package br.com.projeto.miniautorizador.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class SaldoCartaoResponseDTO {
    private BigDecimal saldo;
}
