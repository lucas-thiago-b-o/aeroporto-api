package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "aeroporto", schema = "aeroporto_database")
@Data
public class Aeroporto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "codigo_aeroportuario")
    private String codigoAeroportuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
