package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Classe;
import br.com.companhia.aeroporto.dto.ClasseDTO;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ModelMapping<Classe, ClasseDTO> modelMapping;

    public List<ClasseDTO> findAll() {
        return modelMapping.convertToDtoList(classeRepository.findAll(), ClasseDTO.class);
    }

    public List<ClasseDTO> getAllClassesByVooId(Long id) {
        return modelMapping.convertToDtoList(classeRepository.findAllByVooId(id), ClasseDTO.class);
    }

    public ClasseDTO findById(Long id) {
        return Optional.of(classeRepository.findById(id)).filter(Optional::isPresent)
                .map(m -> modelMapping.convertToDto(m.get(), ClasseDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
