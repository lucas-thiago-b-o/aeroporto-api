package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "classe", schema = "aeroporto_database")
@Data
public class Classe implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "assento_id")
    private Assento assento;

    @Column(name = "valor")
    private Long valor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voo_id")
    private Voo voo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;
}
