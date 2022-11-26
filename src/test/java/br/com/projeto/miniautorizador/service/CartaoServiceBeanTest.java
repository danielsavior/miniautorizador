package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import br.com.projeto.miniautorizador.dto.SaldoCartaoResponseDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CartaoServiceBean.class})
public class CartaoServiceBeanTest {

    @Autowired
    private CartaoServiceBean service;

    @MockBean
    private CartaoRepository repository;

    @Test
    public void validaCriarCartaoSucesso() {
        CartaoRequestDTO requestDTO = TestUtils.createCartaoRequestDTO();
        when(repository.findByNumeroCartao(requestDTO.getNumeroCartao())).thenReturn(Optional.empty());
        CartaoResponseDTO cartaoResponseDTO = service.criarCartao(requestDTO);
        assertFalse(cartaoResponseDTO.getCartaoJaExistia());
    }

    @Test
    public void validaCriarCartaoQueJaExiste() {
        CartaoRequestDTO requestDTO = TestUtils.createCartaoRequestDTO();
        when(repository.findByNumeroCartao(requestDTO.getNumeroCartao())).thenReturn(TestUtils.createOptionalCartao());
        CartaoResponseDTO cartaoResponseDTO = service.criarCartao(requestDTO);
        assertTrue(cartaoResponseDTO.getCartaoJaExistia());
    }

    @Test
    public void validaConsultarSaldo() {
        when(repository.findByNumeroCartao(TestUtils.NUMERO_CARTAO)).thenReturn(TestUtils.createOptionalCartao());
        SaldoCartaoResponseDTO saldoCartaoResponseDTO = service.consultarSaldoDoCartao(TestUtils.NUMERO_CARTAO);
        assertEquals(BigDecimal.ONE, saldoCartaoResponseDTO.getSaldo());
    }

    @Test
    public void validaConsultarSaldoCartaoNaoExiste() {
        when(repository.findByNumeroCartao(TestUtils.NUMERO_CARTAO)).thenReturn(Optional.empty());
        AutorizadorException exception = assertThrows(AutorizadorException.class, () -> service.consultarSaldoDoCartao(TestUtils.NUMERO_CARTAO));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Esse cartão não existe.", exception.getDetail());

    }


}
