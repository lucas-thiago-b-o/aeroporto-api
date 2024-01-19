package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String nomeVoo;

    @Column(name = "")
    private String portaoEmbarque;

    @Column(name = "")
    private LocalDateTime dataHoraVoo;

    @Column(name = "")
    private Long valor;

    @Column(name = "")
    private Long numeroIdentificacao;

    @ManyToOne
    @JoinColumn(name = "assento")
    private Assento assento;

    @ManyToOne
    @JoinColumn(name = "passagem")
    private Aeroporto aeroportoDestino;

    @ManyToOne
    @JoinColumn(name = "passagem")
    private Passageiro passageiro;

    @OneToMany(mappedBy = "bagagem")
    private List<Bagagem> bagagens = new ArrayList<>();
}
