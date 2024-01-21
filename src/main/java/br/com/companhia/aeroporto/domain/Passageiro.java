package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Passageiro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String nomeCompleto;

    @Column(name = "")
    private Long cpf;

    @Column(name = "")
    private Long rg;

    @Column(name = "")
    private Long passaporte;

    @Column(name = "")
    private LocalDateTime dataNascimento;

    @Column(name = "")
    private Long telefone;

    @Column(name = "")
    private Long contatoEmergencia;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Bagagem bagagem;
}
