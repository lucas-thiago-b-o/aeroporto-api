package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.service.AeroportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/aeroportos")
public class AeroportoResource {

    @Autowired
    private AeroportoService aeroportoService;

    @GetMapping(value = "/aeroporto")
    public ResponseEntity<List<AeroportoDTO>> findAll() {
            return Optional.ofNullable(aeroportoService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

}
