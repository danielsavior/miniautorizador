package br.com.projeto.miniautorizador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaldoCartaoResponseDTO {

    private BigDecimal saldo;
}
