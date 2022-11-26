package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.entity.Cartao;

import java.math.BigDecimal;
import java.util.Optional;

public class TestUtils {

    public static final String NUMERO_CARTAO = "6549873025634501";

    static final String SENHA_CARTAO = "6549873025634501";

    static final BigDecimal SALDO_CARTAO = BigDecimal.ONE;


    public static Optional<Cartao> createOptionalCartao() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(NUMERO_CARTAO);
        cartao.setSenha(SENHA_CARTAO);
        cartao.setSaldo(SALDO_CARTAO);
        return Optional.of(cartao);
    }

    static CartaoRequestDTO createCartaoRequestDTO() {
        return CartaoRequestDTO.builder().numeroCartao(NUMERO_CARTAO).senha(SENHA_CARTAO).build();
    }

    public static TransacaoRequestDTO createTransacaoRequestDTO(BigDecimal saldo) {
        return TransacaoRequestDTO.builder().numeroCartao(NUMERO_CARTAO).senha(SENHA_CARTAO).valor(saldo).build();
    }
}
