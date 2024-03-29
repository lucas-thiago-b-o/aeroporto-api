package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AeroportoDTO {

    private Long id;
    private String nome;
    private String codigoAeroportuario;
    private CidadeDTO cidade;
}
