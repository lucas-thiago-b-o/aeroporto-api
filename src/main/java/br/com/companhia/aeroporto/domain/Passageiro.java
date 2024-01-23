package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "passageiro", schema = "aeroporto_database")
@Data
public class Passageiro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "rg")
    private Long rg;

    @Column(name = "passaporte")
    private Long passaporte;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "contato_emergencia")
    private Long contatoEmergencia;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bagagem_id")
    private Bagagem bagagem;
}
