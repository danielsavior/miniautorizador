package br.com.projeto.miniautorizador.service.validators;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.service.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {ValidadorDeTransacaoBean.class})
public class ValidadorDeTransacaoBeanTest {

    @Autowired
    private ValidadorDeTransacaoBean validador;

    @Test
    public void validaTransacaoSenhaIncorreta() {
        TransacaoRequestDTO requestDTO = TransacaoRequestDTO.builder().numeroCartao(TestUtils.NUMERO_CARTAO).senha("4321").valor(BigDecimal.ONE).build();
        AutorizadorException exception = assertThrows(AutorizadorException.class, () -> validador.validaTransacao(requestDTO, TestUtils.createOptionalCartao().get()));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("SENHA_INVALIDA", exception.getDetail());
    }

    @Test
    public void validaTransacaoSaldoInsuficiente() {
        AutorizadorException exception = assertThrows(AutorizadorException.class, () -> validador.validaTransacao(TestUtils.createTransacaoRequestDTO(BigDecimal.TEN), TestUtils.createOptionalCartao().get()));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("SALDO_INSUFICIENTE", exception.getDetail());
    }
}
