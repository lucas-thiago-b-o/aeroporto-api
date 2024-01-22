package br.com.companhia.aeroporto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AssentoDTO implements Serializable {

    private Long id;
    private String nome;
}
