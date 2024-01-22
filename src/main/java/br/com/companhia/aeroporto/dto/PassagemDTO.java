package br.com.companhia.aeroporto.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PassagemDTO implements Serializable {

    private Long id;
    private String portaoEmbarque;
    private LocalDateTime dataHoraVoo;
    private Long valor;
    private Long numeroIdentificacao;
    private VooDTO voo;
}
