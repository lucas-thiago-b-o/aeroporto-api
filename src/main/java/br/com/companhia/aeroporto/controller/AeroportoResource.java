package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.service.AeroportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/aeroporto/{id}")
    public ResponseEntity<AeroportoDTO> findById(@PathVariable Long id) {
        return Optional.ofNullable(aeroportoService.findById(id)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/aeroporto/cidade/{idCidade}")
    public ResponseEntity<List<AeroportoDTO>> findAllByCidadeId(@PathVariable Long idCidade) {
        return Optional.ofNullable(aeroportoService.findAllByCidadeId(idCidade)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/aeroporto/estado/{idEstado}")
    public ResponseEntity<List<AeroportoDTO>> findAllByEstadoId(@PathVariable Long idEstado) {
        return Optional.ofNullable(aeroportoService.findAllByUfId(idEstado)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

}
