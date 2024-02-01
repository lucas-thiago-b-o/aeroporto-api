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
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "passaporte")
    private String passaporte;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "contato_emergencia")
    private String contatoEmergencia;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bagagem_id")
    private Bagagem bagagem;
}
