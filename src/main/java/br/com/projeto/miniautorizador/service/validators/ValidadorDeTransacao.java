package br.com.projeto.miniautorizador.service.validators;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.entity.Cartao;
import br.com.projeto.miniautorizador.exception.AutorizadorException;


public interface ValidadorDeTransacao {

    void validaTransacao(TransacaoRequestDTO dto, Cartao cartao) throws AutorizadorException;
}
