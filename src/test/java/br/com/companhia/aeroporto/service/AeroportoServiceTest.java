package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Aeroporto;
import br.com.companhia.aeroporto.dto.AeroportoDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.AeroportoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AeroportoServiceTest {

    @Mock
    AeroportoRepository aeroportoRepository;

    @Mock
    ModelMapping<Aeroporto, AeroportoDTO> modelMapping;

    @InjectMocks
    AeroportoService aeroportoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new AeroportoDTO()));

        List<AeroportoDTO> result = aeroportoService.findAll();
        Assertions.assertEquals(List.of(new AeroportoDTO()), result);
    }

    @Test
    void testFindAllByCidadeId() {
        when(aeroportoRepository.findAeroportoByCidade_Id(anyLong())).thenReturn(List.of(new Aeroporto()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new AeroportoDTO()));

        List<AeroportoDTO> result = aeroportoService.findAllByCidadeId(1L);
        Assertions.assertEquals(List.of(new AeroportoDTO()), result);
    }

    @Test
    void testFindAllByUfId() {
        when(aeroportoRepository.findAeroportoByUfId(anyLong())).thenReturn(List.of(new Aeroporto()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new AeroportoDTO()));

        List<AeroportoDTO> result = aeroportoService.findAllByUfId(1L);
        Assertions.assertEquals(List.of(new AeroportoDTO()), result);
    }

    @Test
    void testFindById() {
        when(aeroportoRepository.findById(1L)).thenReturn(Optional.of(new Aeroporto()));
        when(modelMapping.convertToDto(any(), any())).thenReturn(new AeroportoDTO());

        AeroportoDTO result = aeroportoService.findById(1L);
        Assertions.assertEquals(new AeroportoDTO(), result);
    }
}