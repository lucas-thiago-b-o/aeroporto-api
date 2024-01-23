package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.ComprarPassagemDTO;
import br.com.companhia.aeroporto.dto.PassagemDTO;
import br.com.companhia.aeroporto.service.PassagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/passagens")
public class PassagemResource {

    @Autowired
    private PassagemService passagemService;

    @GetMapping(value = "/passagem")
    public ResponseEntity<List<PassagemDTO>> findAll() {
        return Optional.ofNullable(passagemService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @PostMapping(value = "/passagem")
    public ResponseEntity<String> comprarPassagem(@RequestBody ComprarPassagemDTO comprarPassagemDTO) {
        return Optional.ofNullable(passagemService.comprarPassagem(comprarPassagemDTO)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(value = "/passagem")
    public ResponseEntity<String> cancelarPassagem(@RequestBody PassagemDTO passagemDTO) {
        return Optional.ofNullable(passagemService.cancelarPassagem(passagemDTO)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
}
