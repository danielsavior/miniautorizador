package br.com.projeto.miniautorizador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDTO {

    @JsonProperty(value = "Status Code", required = true)
    private String codigo;
    @JsonProperty(value = "Body", required = true)
    private String motivo;
}
