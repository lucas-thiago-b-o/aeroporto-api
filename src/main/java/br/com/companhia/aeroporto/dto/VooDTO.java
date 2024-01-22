package br.com.companhia.aeroporto.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VooDTO implements Serializable {

    private Long id;
    private String nome;
    private LocalDateTime dataHoraMarcado;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraPrevisao;
    private LocalDateTime dataHoraChegada;
    private AeroportoDTO aeroportoOrigem;
    private AeroportoDTO aeroportoDestino;
    private List<PassageiroDTO> passageiros;
    private List<ClasseDTO> classes;
}
