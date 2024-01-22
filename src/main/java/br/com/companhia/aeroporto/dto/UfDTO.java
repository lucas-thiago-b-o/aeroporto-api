package br.com.companhia.aeroporto.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UfDTO implements Serializable {

    private Long id;
    private String nome;
}
