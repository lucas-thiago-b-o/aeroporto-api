package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClasseDTO {

    private Long id;
    private String nome;
    private AssentoDTO assentos;
    private Long valor;
    private VooDTO voo;
    private PassageiroDTO passageiro;
}
