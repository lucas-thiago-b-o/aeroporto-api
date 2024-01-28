package br.com.companhia.aeroporto.controller;

import br.com.companhia.aeroporto.dto.ClasseDTO;
import br.com.companhia.aeroporto.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/classes")
public class ClasseResource {

    @Autowired
    private ClasseService classeService;

    @GetMapping(value = "/classe")
    public ResponseEntity<List<ClasseDTO>> findAll() {
        return Optional.ofNullable(classeService.findAll()).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/classe/{id}")
    public ResponseEntity<ClasseDTO> findById(@PathVariable Long id) {
        return Optional.ofNullable(classeService.findById(id)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/classe/voo/{id}")
    public ResponseEntity<List<ClasseDTO>> getAllClassesByVooId(@PathVariable Long id) {
        return Optional.ofNullable(classeService.getAllClassesByVooId(id)).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

}
