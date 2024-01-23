package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassageiroAssentoDTO {

    private PassageiroDTO passageiroDTO;
    private Long classeId;
}
