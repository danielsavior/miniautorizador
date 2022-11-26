package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.repository.CartaoRepository;
import br.com.projeto.miniautorizador.service.validators.ValidadorDeTransacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TransacaoServiceBean.class})
public class TransacaoServiceBeanTest {

    @Autowired
    private TransacaoServiceBean service;

    @MockBean
    private CartaoRepository repository;

    @MockBean
    private ValidadorDeTransacao validador;


    @Test
    public void validaEfetuarTransacaoCartaoInexistente() {
        TransacaoRequestDTO requestDTO = TestUtils.createTransacaoRequestDTO(BigDecimal.ONE);
        when(repository.findByNumeroCartao(requestDTO.getNumeroCartao())).thenReturn(Optional.empty());
        AutorizadorException exception = assertThrows(AutorizadorException.class, () -> service.efetuarTransacao(requestDTO));
        assertEquals("CARTAO_INEXISTENTE", exception.getDetail());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

}
