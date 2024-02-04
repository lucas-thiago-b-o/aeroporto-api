package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Assento;
import br.com.companhia.aeroporto.domain.Classe;
import br.com.companhia.aeroporto.domain.Passageiro;
import br.com.companhia.aeroporto.domain.Voo;
import br.com.companhia.aeroporto.dto.AssentoDTO;
import br.com.companhia.aeroporto.dto.ClasseDTO;
import br.com.companhia.aeroporto.dto.PassageiroDTO;
import br.com.companhia.aeroporto.dto.VooDTO;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.ClasseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ClasseServiceTest {

    @Mock
    ClasseRepository classeRepository;

    @Mock
    ModelMapping<Classe, ClasseDTO> classeModelMapping;

    @Mock
    ModelMapping<Voo, VooDTO> vooModelMapping;

    @Mock
    ModelMapping<Assento, AssentoDTO> assentoModelMapping;

    @Mock
    ModelMapping<Passageiro, PassageiroDTO> passageiroModelMapping;

    @InjectMocks
    ClasseService classeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(classeModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new ClasseDTO()));
        when(vooModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));
        when(assentoModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new AssentoDTO()));
        when(passageiroModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new PassageiroDTO()));

        List<ClasseDTO> result = classeService.findAll();
        Assertions.assertEquals(List.of(new ClasseDTO()), result);
    }

    @Test
    void testGetQuantPassageirosByVoo() {
        when(classeRepository.pegaQuantPassageirosByVoo(anyLong())).thenReturn(0);

        Integer result = classeService.getQuantPassageirosByVoo(1L);
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetAllClassesByVooId() {
        Classe classe = new Classe();
        classe.setId(1L);
        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId(1L);

        classeDTO.setVoo(new VooDTO());
        classeDTO.setAssentos(new AssentoDTO());
        classe.setPassageiro(new Passageiro());
        classeDTO.setPassageiro(new PassageiroDTO());

        when(classeRepository.findAllByVooId(anyLong())).thenReturn(List.of(classe));
        when(classeModelMapping.convertToDto(any(), any())).thenReturn(classeDTO);
        when(classeModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(classeDTO));
        when(vooModelMapping.convertToDto(any(), any())).thenReturn(new VooDTO());
        when(vooModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new VooDTO()));
        when(assentoModelMapping.convertToDto(any(), any())).thenReturn(new AssentoDTO());
        when(assentoModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new AssentoDTO()));
        when(passageiroModelMapping.convertToDto(any(), any())).thenReturn(new PassageiroDTO());
        when(passageiroModelMapping.convertToDtoList(any(), any())).thenReturn(List.of(new PassageiroDTO()));

        List<ClasseDTO> result = classeService.getAllClassesByVooId(1L);
        Assertions.assertEquals(List.of(classeDTO), result);
    }

    @Test
    void testFindById() {
        when(classeRepository.findById(1L)).thenReturn(Optional.of(new Classe()));
        when(classeModelMapping.convertToDto(any(), any())).thenReturn(new ClasseDTO());
        when(vooModelMapping.convertToDto(any(), any())).thenReturn(new VooDTO());
        when(assentoModelMapping.convertToDto(any(), any())).thenReturn(new AssentoDTO());
        when(passageiroModelMapping.convertToDto(any(), any())).thenReturn(new PassageiroDTO());

        ClasseDTO result = classeService.findById(1L);
        Assertions.assertEquals(new ClasseDTO(), result);
    }
}