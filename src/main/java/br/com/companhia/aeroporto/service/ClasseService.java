package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Assento;
import br.com.companhia.aeroporto.domain.Classe;
import br.com.companhia.aeroporto.domain.Passageiro;
import br.com.companhia.aeroporto.domain.Voo;
import br.com.companhia.aeroporto.dto.AssentoDTO;
import br.com.companhia.aeroporto.dto.ClasseDTO;
import br.com.companhia.aeroporto.dto.PassageiroDTO;
import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ModelMapping<Classe, ClasseDTO> classeModelMapping;

    @Autowired
    private ModelMapping<Voo, VooDTO> vooModelMapping;

    @Autowired
    private ModelMapping<Assento, AssentoDTO> assentoModelMapping;

    @Autowired
    private ModelMapping<Passageiro, PassageiroDTO> passageiroModelMapping;

    public List<ClasseDTO> findAll() {
        return classeModelMapping.convertToDtoList(classeRepository.findAll(), ClasseDTO.class);
    }

    public List<ClasseDTO> getAllClassesByVooId(Long id) {
        List<Classe> classesEntity = classeRepository.findAllByVooId(id);
        List<ClasseDTO> classesDTO = classeModelMapping.convertToDtoList(classesEntity, ClasseDTO.class);

        classesDTO.forEach(classe -> {
            VooDTO vooDTO = vooModelMapping.convertToDto(classesEntity.stream().filter(f -> f.getId().equals(classe.getId())).toList().get(0).getVoo(), VooDTO.class);
            classe.setVoo(vooDTO);

            AssentoDTO assentoDTO = assentoModelMapping.convertToDto(classesEntity.stream().filter(f -> f.getId().equals(classe.getId())).toList().get(0).getAssento(), AssentoDTO.class);

            PassageiroDTO passageiroDTO = null;
            if (Objects.nonNull(classesEntity.stream().filter(f -> f.getId().equals(classe.getId())).toList().get(0).getPassageiro()))
                passageiroDTO = passageiroModelMapping.convertToDto(classesEntity.stream().filter(f -> f.getId().equals(classe.getId())).toList().get(0).getPassageiro(), PassageiroDTO.class);

            classe.setPassageiro(passageiroDTO);
            classe.setAssentos(assentoDTO);
        });

        return classesDTO;
    }

    public ClasseDTO findById(Long id) {
        return Optional.of(classeRepository.findById(id)).filter(Optional::isPresent)
                .map(m -> classeModelMapping.convertToDto(m.get(), ClasseDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
