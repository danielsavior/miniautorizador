package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import br.com.projeto.miniautorizador.dto.SaldoCartaoResponseDTO;
import br.com.projeto.miniautorizador.entity.Cartao;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class CartaoServiceBean implements CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Override
    public CartaoResponseDTO criarCartao(CartaoRequestDTO dto) throws AutorizadorException {

        CartaoResponseDTO cartaoResponseDTO = CartaoResponseDTO.builder().numeroCartao(dto.getNumeroCartao())
                                                               .senha(dto.getSenha()).build();
        Optional<Cartao> optionalCartao = repository.findByNumeroCartao(dto.getNumeroCartao());
        if (optionalCartao.isPresent()) {
            cartaoResponseDTO.setCartaoJaExistia(Boolean.TRUE);
            return cartaoResponseDTO;
        }
        cartaoResponseDTO.setCartaoJaExistia(Boolean.FALSE);
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(dto.getNumeroCartao());
        cartao.setSenha(dto.getSenha());
        cartao.setSaldo(BigDecimal.valueOf(500));
        repository.save(cartao);

        return cartaoResponseDTO;
    }

    @Override
    public SaldoCartaoResponseDTO consultarSaldoDoCartao(String numeroCartao) throws AutorizadorException {
        Cartao cartao = repository.findByNumeroCartao(numeroCartao)
                                  .orElseThrow(() -> AutorizadorException.builder().status(HttpStatus.NOT_FOUND)
                                                                         .detail("Esse cartão não existe.").build());
        return SaldoCartaoResponseDTO.builder().saldo(cartao.getSaldo()).build();
    }
}
