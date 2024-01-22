package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Cidade;
import br.com.companhia.aeroporto.dto.CidadeDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ModelMapping<Cidade, CidadeDTO> modelMapping;

    public List<CidadeDTO> findAll() {
        return modelMapping.convertToDtoList(cidadeRepository.findAll(), CidadeDTO.class);
    }

}
