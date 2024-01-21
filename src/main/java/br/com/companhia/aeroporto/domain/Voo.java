package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "", schema = "")
@Data
public class Voo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String nome;

    @Column(name = "")
    private LocalDateTime dataHoraMarcado;

    @Column(name = "")
    private LocalDateTime dataHoraPartida;

    @Column(name = "")
    private LocalDateTime dataHoraPrevisao;

    @Column(name = "")
    private LocalDateTime dataHoraChegada;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="aeroportoOrigemId", referencedColumnName="id")
    private Aeroporto aeroportoOrigem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="aeroportoDestinoId", referencedColumnName="id")
    private Aeroporto aeroportoDestino;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Passageiro passageiro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "")
    private Classe classe;
}
