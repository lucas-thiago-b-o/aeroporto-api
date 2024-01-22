package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Aeroporto;
import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository aeroportoRepository;

    @Autowired
    private ModelMapping<Aeroporto, AeroportoDTO> modelMapping;

    public List<AeroportoDTO> findAll() {
        return modelMapping.convertToDtoList(aeroportoRepository.findAll(), AeroportoDTO.class);
    }

}
