package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BagagemDTO {

    private Long id;
    private String numeroIdentificacao;
    private Boolean isDespachada;
}
