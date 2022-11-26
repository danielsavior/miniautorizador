package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;

/**
 * Interface criada para seguir conceitos de SOLID
 */
public interface TransacaoService {

    void efetuarTransacao(TransacaoRequestDTO dto) throws AutorizadorException;
}
