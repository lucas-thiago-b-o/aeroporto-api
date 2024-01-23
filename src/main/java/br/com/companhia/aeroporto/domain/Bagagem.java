package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "bagagem", schema = "aeroporto_database")
@Data
public class Bagagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_identificacao")
    private Long numeroIdentificacao;

    @Column(name = "is_despachada")
    private Boolean isDespachada;

}
