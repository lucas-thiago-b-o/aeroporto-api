package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "passagem", schema = "aeroporto_database")
@Data
public class Passagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "portao_embarque")
    private String portaoEmbarque;

    @Column(name = "status")
    private String status;

    @Column(name = "data_hora_voo")
    private LocalDateTime dataHoraVoo;

    @Column(name = "valor")
    private Long valor;

    @Column(name = "numero_identificacao")
    private String numeroIdentificacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voo_id")
    private Voo voo;
}
