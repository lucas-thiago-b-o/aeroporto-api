package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ComprarPassagemDTO {

    private PassagemDTO passagemDTO;
    private List<PassageiroAssentoDTO> passageirosAssentoDTO;
}
