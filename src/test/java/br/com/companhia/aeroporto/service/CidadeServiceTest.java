package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Cidade;
import br.com.companhia.aeroporto.dto.CidadeDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.CidadeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class CidadeServiceTest {
    @Mock
    CidadeRepository cidadeRepository;

    @Mock
    ModelMapping<Cidade, CidadeDTO> modelMapping;

    @InjectMocks
    CidadeService cidadeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new CidadeDTO()));

        List<CidadeDTO> result = cidadeService.findAll();
        Assertions.assertEquals(List.of(new CidadeDTO()), result);
    }
}