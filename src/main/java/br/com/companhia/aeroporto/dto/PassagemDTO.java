package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PassagemDTO {

    private Long id;
    private String portaoEmbarque;
    private LocalDateTime dataHoraVoo;
    private Long valor;
    private String numeroIdentificacao;
    private VooDTO voo;
}
