package br.com.companhia.aeroporto.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "voo", schema = "aeroporto_database")
@Data
public class Voo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "status")
    private String status;

    @Column(name = "portao_embarque")
    private String portaoEmbarque;

    @Column(name = "data_hora_marcado")
    private LocalDateTime dataHoraMarcado;

    @Column(name = "data_hora_partida")
    private LocalDateTime dataHoraPartida;

    @Column(name = "data_hora_previsao")
    private LocalDateTime dataHoraPrevisao;

    @Column(name = "data_hora_chegada")
    private LocalDateTime dataHoraChegada;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="aeroporto_origem_id", referencedColumnName="id")
    private Aeroporto aeroportoOrigem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="aeroporto_destino_id", referencedColumnName="id")
    private Aeroporto aeroportoDestino;
}

/*
    Status disponíveis:

    Programado: Indica que o voo está planejado para ocorrer em uma data e hora específicas.
    Check-in: Os passageiros fazem o check-in para o voo, seja online, no aeroporto ou por outros meios.
    Embarque: Passageiros começam a embarcar no avião.
    Atrasado: O voo não está seguindo o cronograma original e será realizado em um horário posterior ao programado.
    Cancelado: O voo foi cancelado e não ocorrerá.
    Em voo: O avião está no ar, voando para o destino.
    Desembarque: Os passageiros estão saindo do avião após a chegada ao destino.
    Chegou: O avião pousou no aeroporto de destino.
    Bagagem despachada: A bagagem dos passageiros foi transferida do avião para a área de retirada de bagagem.
    Concluído: Todas as fases do voo foram concluídas, e a viagem chegou ao fim.
*/
