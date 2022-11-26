package br.com.projeto.miniautorizador.service.validators;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.entity.Cartao;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import org.springframework.http.HttpStatus;

public class ValidadorDeTransacaoBean implements ValidadorDeTransacao {

    @Override
    public void validaTransacao(TransacaoRequestDTO dto, Cartao cartao) throws AutorizadorException {

        if (!dto.getSenha().equals(cartao.getSenha())) {
            throw AutorizadorException.builder().status(HttpStatus.NOT_FOUND).detail("SENHA_INVALIDA").build();
        }

        if (dto.getValor().compareTo(cartao.getSaldo()) > 0){
            throw AutorizadorException.builder().status(HttpStatus.NOT_FOUND).detail("SALDO_INSUFICIENTE").build();
        }
    }
}
