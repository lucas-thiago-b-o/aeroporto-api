package br.com.companhia.aeroporto.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class BagagemDTO implements Serializable {

    private Long id;
    private Long numeroIdentificacao;
    private Boolean isDespachada;
}
