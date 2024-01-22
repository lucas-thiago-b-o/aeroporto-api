package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Aeroporto;
import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    @Autowired
    private ModelMapping<Aeroporto, AeroportoDTO> modelMapping;

    public List<AeroportoDTO> findAll() {
        return modelMapping.convertToDtoList(aeroportoRepository.findAll(), AeroportoDTO.class);
    }

    public List<AeroportoDTO> findAllByCidadeId(Long id) {
        return modelMapping.convertToDtoList(aeroportoRepository.findAeroportoByCidade_Id(id), AeroportoDTO.class);
    }

    public List<AeroportoDTO> findAllByUfId(Long id) {
        return modelMapping.convertToDtoList(aeroportoRepository.findAeroportoByUfId(id), AeroportoDTO.class);
    }

    public AeroportoDTO findById(Long id) {
        return Optional.of(aeroportoRepository.findById(id)).filter(Optional::isPresent)
                .map(m -> modelMapping.convertToDto(m.get(), AeroportoDTO.class))
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
