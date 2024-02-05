package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.CidadeDTO;
import br.com.companhia.aeroporto.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cidades")
public class CidadeResource {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping(value = "/cidade")
    public ResponseEntity<List<CidadeDTO>> findAll() {
        return Optional.ofNullable(cidadeService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

}
