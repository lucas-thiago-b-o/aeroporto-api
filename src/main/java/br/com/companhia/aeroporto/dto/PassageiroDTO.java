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
    private Long cpf;
    private Long rg;
    private Long passaporte;
    private LocalDateTime dataNascimento;
    private Long telefone;
    private Long contatoEmergencia;
    private List<BagagemDTO> bagagens;
}
