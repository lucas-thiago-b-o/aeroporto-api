package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VooDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataHoraMarcado;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraPrevisao;
    private LocalDateTime dataHoraChegada;
    private AeroportoDTO aeroportoOrigem;
    private AeroportoDTO aeroportoDestino;
}
