package br.com.companhia.aeroporto.dto;

import br.com.companhia.aeroporto.enums.UsuarioRole;

public record RegisterDTO(String username, String password, UsuarioRole role) {

}
