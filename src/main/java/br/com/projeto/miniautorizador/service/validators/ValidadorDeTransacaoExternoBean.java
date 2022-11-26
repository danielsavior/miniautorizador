package br.com.projeto.miniautorizador.service.validators;

import br.com.projeto.miniautorizador.dto.TransacaoRequestDTO;
import br.com.projeto.miniautorizador.entity.Cartao;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import org.apache.commons.lang3.NotImplementedException;


/**
 * Classe criada para extensão que pode ser feita futuramente para utilizar regras que podem ser
 * controladas externa a aplicação em um BPMN por exemplo. Para usar esse bean é preciso adicionar
 * a propertie application.validador-externo=true
 */
public class ValidadorDeTransacaoExternoBean implements ValidadorDeTransacao {

    @Override
    public void validaTransacao(TransacaoRequestDTO dto, Cartao cartao) throws AutorizadorException {
        throw new NotImplementedException();
    }
}
