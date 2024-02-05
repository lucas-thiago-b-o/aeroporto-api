package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/voos")
public class VooResource {

    private final VooService vooService;

    @Autowired
    public VooResource(VooService vooService) {
        this.vooService = vooService;
    }

    @GetMapping(value = "/voo")
    public ResponseEntity<List<VooDTO>> findAll() {
        return Optional.ofNullable(vooService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/voo/{id}")
    public ResponseEntity<VooDTO> findById(@PathVariable Long id) {
        return Optional.ofNullable(vooService.findById(id)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/voo/aeroporto/{id}")
    public ResponseEntity<List<VooDTO>> findAllByAeroporoId(@PathVariable Long id) {
        return Optional.ofNullable(vooService.findAllByAeroporoId(id)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/voo/aeroporto/cidade/{idCidade}")
    public ResponseEntity<List<VooDTO>> findAllByCidadeId(@PathVariable Long idCidade) {
        return Optional.ofNullable(vooService.findAllByCidadeId(idCidade)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/voo/aeroporto/cidade_origem/{idOrigem}/cidade_destino/{idDestino}")
    public ResponseEntity<List<VooDTO>> findAllByCidadesIds(@PathVariable Long idOrigem, @PathVariable Long idDestino) {
        return Optional.ofNullable(vooService.findAllByCidadesIds(idOrigem, idDestino)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/voo/aeroporto/estado/{idEstado}")
    public ResponseEntity<List<VooDTO>> findAllByEstadoId(@PathVariable Long idEstado) {
        return Optional.ofNullable(vooService.findAllByUfId(idEstado)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(value = "/voo/cancelar/{idVoo}")
    public ResponseEntity<String> cancelarPassagem(@PathVariable Long idVoo) {
        return Optional.ofNullable(vooService.cancelarVoo(idVoo)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
}
