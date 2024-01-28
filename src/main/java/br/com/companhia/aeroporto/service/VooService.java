package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Voo;
import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.ClasseRepository;
import br.com.companhia.aeroporto.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VooService {

    @Autowired
    private VooRepository vooRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ModelMapping<Voo, VooDTO> modelMapping;

    public List<VooDTO> findAllByCidadeId(Long id) {
        return modelMapping.convertToDtoList(vooRepository.findAllByCidadeId(id), VooDTO.class);
    }

    public List<VooDTO> findAllByUfId(Long id) {
        return modelMapping.convertToDtoList(vooRepository.findAllByUfId(id), VooDTO.class);
    }

    public List<VooDTO> findAllByAeroporoId(Long id) {
        return modelMapping.convertToDtoList(vooRepository.findAllByAeroportoOrigemId(id), VooDTO.class);
    }

    public List<VooDTO> findAllByCidadesIds(Long idOrigem, Long idDestino) {
        return modelMapping.convertToDtoList(vooRepository.findAllByCidadesIds(idOrigem, idDestino), VooDTO.class);
    }

    public VooDTO findById(Long id) {
        return Optional.of(vooRepository.findById(id)).filter(Optional::isPresent)
                .map(m -> modelMapping.convertToDto(m.get(), VooDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
