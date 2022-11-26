package br.com.projeto.miniautorizador.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Cartao {

    public Cartao() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, unique = true, nullable = false)
    private String numeroCartao;

    @Column(length = 20, nullable = false)
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldo;
}
