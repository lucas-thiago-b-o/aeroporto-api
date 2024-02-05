package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Voo;
import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.ClasseRepository;
import br.com.companhia.aeroporto.repository.PassagemRepository;
import br.com.companhia.aeroporto.repository.VooRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class VooServiceTest {

    @Mock
    VooRepository vooRepository;

    @Mock
    ClasseRepository classeRepository;

    @Mock
    PassagemRepository passagemRepository;

    @Mock
    ModelMapping<Voo, VooDTO> modelMapping;

    @InjectMocks
    VooService vooService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));

        List<VooDTO> result = vooService.findAll();
        Assertions.assertEquals(List.of(new VooDTO()), result);
    }

    @Test
    void testFindAllByCidadeId() {
        when(vooRepository.findAllByCidadeId(anyLong())).thenReturn(List.of(new Voo()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));

        List<VooDTO> result = vooService.findAllByCidadeId(1L);
        Assertions.assertEquals(List.of(new VooDTO()), result);
    }

    @Test
    void testFindAllByUfId() {
        when(vooRepository.findAllByUfId(anyLong())).thenReturn(List.of(new Voo()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));

        List<VooDTO> result = vooService.findAllByUfId(1L);
        Assertions.assertEquals(List.of(new VooDTO()), result);
    }

    @Test
    void testFindAllByAeroporoId() {
        when(vooRepository.findAllByAeroportoOrigemId(anyLong())).thenReturn(List.of(new Voo()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));

        List<VooDTO> result = vooService.findAllByAeroporoId(1L);
        Assertions.assertEquals(List.of(new VooDTO()), result);
    }

    @Test
    void testFindAllByCidadesIds() {
        when(vooRepository.findAllByCidadesIds(anyLong(), anyLong())).thenReturn(List.of(new Voo()));
        when(modelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));

        List<VooDTO> result = vooService.findAllByCidadesIds(1L, 1L);
        Assertions.assertEquals(List.of(new VooDTO()), result);
    }

    @Test
    void testFindById() {
        when(vooRepository.findById(1L)).thenReturn(Optional.of(new Voo()));
        when(modelMapping.convertToDto(any(), any())).thenReturn(new VooDTO());

        VooDTO result = vooService.findById(1L);
        Assertions.assertEquals(new VooDTO(), result);
    }

    @Test
    void testCancelarVoo() {
        String result = vooService.cancelarVoo(1L);
        Assertions.assertEquals("Voo cancelado com sucesso!", result);
    }
}