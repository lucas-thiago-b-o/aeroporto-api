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

    @ManyToOne
    @JoinColumn(name = "aeroporto_id")
    private Aeroporto aeroportoOrigem;

    @ManyToOne
    @JoinColumn(name = "aeroporto_id")
    private Aeroporto aeroportoDestino;

    @OneToMany(mappedBy = "passageiro")
    private List<Passageiro> passageiros = new ArrayList<>();

    @OneToMany(mappedBy = "passageiro")
    private List<Classe> classes = new ArrayList<>();

    @OneToMany(mappedBy = "bagagem")
    private List<Bagagem> bagagens = new ArrayList<>();
}
