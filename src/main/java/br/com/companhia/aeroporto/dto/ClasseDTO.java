package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClasseDTO {

    private Long id;
    private String nome;
    private List<AssentoDTO> assentos;
    private VooDTO vooDTO;
}
