package br.com.companhia.aeroporto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PassageiroDTO {


    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String rg;
    private String passaporte;
    private LocalDateTime dataNascimento;
    private String telefone;
    private String contatoEmergencia;
    private List<BagagemDTO> bagagens;
}
