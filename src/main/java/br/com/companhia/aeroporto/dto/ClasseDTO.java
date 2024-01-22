package br.com.companhia.aeroporto.dto;

import br.com.companhia.aeroporto.domain.Assento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ClasseDTO implements Serializable {

    private Long id;
    private String nome;
    private List<Assento> assentos;
}
