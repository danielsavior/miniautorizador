package br.com.projeto.miniautorizador.service;


import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.entity.Cartao;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.repository.CartaoRepository;
import br.com.projeto.miniautorizador.service.validators.ValidadorDeTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TransacaoServiceBean implements TransacaoService {

    @Autowired
    private ValidadorDeTransacao validador;

    @Autowired
    private CartaoRepository repository;

    @Override
    public synchronized void efetuarTransacao(TransacaoRequestDTO dto) throws AutorizadorException {
        Cartao cartao = repository.findByNumeroCartao(dto.getNumeroCartao())
                                  .orElseThrow(() -> AutorizadorException.builder().status(HttpStatus.NOT_FOUND)
                                                                         .detail("CARTAO_INEXISTENTE").build());
        validador.validaTransacao(dto, cartao);
        cartao.setSaldo(cartao.getSaldo().subtract(dto.getValor()));
        repository.saveAndFlush(cartao);
    }
}
