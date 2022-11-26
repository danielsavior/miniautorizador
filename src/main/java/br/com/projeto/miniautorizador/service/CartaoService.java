package br.com.projeto.miniautorizador.service;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import br.com.projeto.miniautorizador.dto.SaldoCartaoResponseDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;

/**
 * Interface criada para seguir conceitos de SOLID
 */
public interface CartaoService {

    CartaoResponseDTO criarCartao(CartaoRequestDTO dto) throws AutorizadorException;

    SaldoCartaoResponseDTO consultarSaldoDoCartao(String numeroCartao) throws AutorizadorException;
}
