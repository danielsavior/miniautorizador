package br.com.projeto.miniautorizador.controller;

import br.com.projeto.miniautorizador.dto.CartaoRequestDTO;
import br.com.projeto.miniautorizador.dto.CartaoResponseDTO;
import br.com.projeto.miniautorizador.dto.SaldoCartaoResponseDTO;
import br.com.projeto.miniautorizador.exception.AutorizadorException;
import br.com.projeto.miniautorizador.service.CartaoService;
import br.com.projeto.miniautorizador.service.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CartaoControllerIT {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @MockBean
    private CartaoService service;

    @Test
    public void validaConsultaSaldo() {
        SaldoCartaoResponseDTO responseDTO = SaldoCartaoResponseDTO.builder().saldo(BigDecimal.ONE).build();
        when(service.consultarSaldoDoCartao(TestUtils.NUMERO_CARTAO)).thenReturn(SaldoCartaoResponseDTO.builder()
                                                                                                       .saldo(BigDecimal.ONE)
                                                                                                       .build());
        SaldoCartaoResponseDTO saldo = get(uri + "/autorizador/cartoes/" + TestUtils.NUMERO_CARTAO).then()
                                                                                                   .assertThat()
                                                                                                   .statusCode(HttpStatus.OK.value())
                                                                                                   .extract()
                                                                                                   .as(SaldoCartaoResponseDTO.class);

        String responseString = RestAssured.when()
                                           .request("GET", uri + "/autorizador/cartoes/" + TestUtils.NUMERO_CARTAO)
                                           .then()
                                           .assertThat()
                                           .statusCode(HttpStatus.OK.value())
                                           .extract().asString();
        String expectedString = "{\"saldo\":1}";
        assertThat(saldo.getSaldo()).isEqualTo(responseDTO.getSaldo());
        assertThat(expectedString).isEqualTo(responseString);
    }

    @Test
    public void validaConsultaSaldoCartaoInexistente() {
        when(service.consultarSaldoDoCartao(TestUtils.NUMERO_CARTAO)).thenThrow(AutorizadorException.builder()
                                                                                                    .status(HttpStatus.NOT_FOUND)
                                                                                                    .detail("Esse cartão não existe.")
                                                                                                    .build());
        get(uri + "/autorizador/cartoes/" + TestUtils.NUMERO_CARTAO).then()
                                                                    .assertThat()
                                                                    .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void validaCadastroCartao() {
        CartaoRequestDTO cartao = TestUtils.createCartaoRequestDTO();
        when(service.criarCartao(any(CartaoRequestDTO.class))).thenReturn(CartaoResponseDTO.builder()
                                                                                           .numeroCartao(TestUtils.NUMERO_CARTAO)
                                                                                           .senha("1234")
                                                                                           .cartaoJaExistia(Boolean.FALSE)
                                                                                           .build());
        String responseString = RestAssured.with().body(TestUtils.createCartaoRequestDTO()).when()
                                           .contentType(ContentType.JSON)
                                           .request("POST", uri + "/autorizador/cartoes/")
                                           .then()
                                           .statusCode(HttpStatus.CREATED.value())
                                           .extract()
                                           .asPrettyString();
        String expectedString = "{\n" +
                "    \"numeroCartao\": \"6549873025634501\",\n" +
                "    \"senha\": \"1234\"\n" +
                "}";
        assertThat(responseString).isEqualTo(expectedString);
    }

    @Test
    public void validaCadastroCartaoJaExiste() {
        CartaoRequestDTO cartaoRequestDTO = TestUtils.createCartaoRequestDTO();
        CartaoResponseDTO cartaoResponseDTO = CartaoResponseDTO.builder().numeroCartao(TestUtils.NUMERO_CARTAO)
                                                               .senha("1234")
                                                               .cartaoJaExistia(Boolean.TRUE)
                                                               .build();
        when(service.criarCartao(any(CartaoRequestDTO.class))).thenReturn(cartaoResponseDTO);
        String responseString = RestAssured.with().body(cartaoRequestDTO).when()
                                           .contentType(ContentType.JSON)
                                           .request("POST", uri + "/autorizador/cartoes/")
                                           .then()
                                           .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                                           .extract()
                                           .asPrettyString();
        String expectedString = "{\n" +
                "    \"numeroCartao\": \"6549873025634501\",\n" +
                "    \"senha\": \"1234\"\n" +
                "}";
        assertThat(responseString).isEqualTo(expectedString);
    }
}
