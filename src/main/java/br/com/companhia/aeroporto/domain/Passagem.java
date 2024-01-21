package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "", schema = "")
@Data
public class Passagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String portaoEmbarque;

    @Column(name = "")
    private LocalDateTime dataHoraVoo;

    @Column(name = "")
    private Long valor;

    @Column(name = "")
    private Long numeroIdentificacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Voo voo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Passageiro passageiro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Classe classe;

        /*

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Bagagem bagagem;*/
}
