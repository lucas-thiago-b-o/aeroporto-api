package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.dto.*;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;

class PassagemServiceTest {

    @Mock
    Random RANDOM;

    @Mock
    AssentoRepository assentoRepository;

    @Mock
    ClasseRepository classeRepository;

    @Mock
    PassagemRepository passagemRepository;

    @Mock
    BagagemRepository bagagemRepository;

    @Mock
    PassageiroRepository passageiroRepository;

    @Mock
    ModelMapping<Assento, AssentoDTO> modelMappingAssento;

    @Mock
    ModelMapping<Passagem, PassagemDTO> modelMappingPassagem;

    @Mock
    ModelMapping<Passageiro, PassageiroDTO> modelMappingPassageiro;

    @Mock
    ModelMapping<Bagagem, BagagemDTO> modelMappingBagagem;

    @InjectMocks
    PassagemService passagemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(modelMappingAssento.convertToDtoList(any(), any())).thenReturn(List.of(new AssentoDTO()));
        when(modelMappingPassagem.convertToDtoList(any(), any())).thenReturn(List.of(new PassagemDTO()));
        when(modelMappingPassageiro.convertToDtoList(any(), any())).thenReturn(List.of(new PassageiroDTO()));
        when(modelMappingBagagem.convertToDtoList(any(), any())).thenReturn(List.of(new BagagemDTO()));

        List<PassagemDTO> result = passagemService.findAll();
        Assertions.assertEquals(List.of(new PassagemDTO()), result);
    }

    @Test
    void testFindAllByUuidUsuario() {
        Passageiro passageiro = new Passageiro();
        passageiro.setId(1L);

        Classe classe = new Classe();
        classe.setId(1L);
        classe.setPassageiro(passageiro);

        Passagem passagem = new Passagem();
        passagem.setUuidUsuario("uuidUsuario");
        passagem.setClasse(classe);

        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId(1L);

        PassagemDTO passagemDTO = new PassagemDTO();
        passagemDTO.setUuidUsuario("uuidUsuario");
        passagemDTO.setClasse(classeDTO);

        when(assentoRepository.findAssentoByClasseId(anyLong())).thenReturn(new Assento());
        when(passagemRepository.findAllByUuidUsuario(anyString())).thenReturn(List.of(passagem));
        when(bagagemRepository.findBagagensByPassageiroId(anyLong())).thenReturn(List.of(new Bagagem()));
        when(modelMappingAssento.convertToDto(any(), any())).thenReturn(new AssentoDTO());
        when(modelMappingAssento.convertToDtoList(any(), any())).thenReturn(List.of(new AssentoDTO()));
        when(modelMappingPassagem.convertToDto(any(), any())).thenReturn(passagemDTO);
        when(modelMappingPassagem.convertToDtoList(any(), any())).thenReturn(List.of(passagemDTO));
        when(modelMappingPassageiro.convertToDto(any(), any())).thenReturn(new PassageiroDTO());
        when(modelMappingPassageiro.convertToDtoList(any(), any())).thenReturn(List.of(new PassageiroDTO()));
        when(modelMappingBagagem.convertToDto(any(), any())).thenReturn(new BagagemDTO());
        when(modelMappingBagagem.convertToDtoList(any(), any())).thenReturn(List.of(new BagagemDTO()));

        List<PassagemDTO> result = passagemService.findAllByUuidUsuario("uuidUsuario");
        Assertions.assertEquals(List.of(passagemDTO), result);
    }

    @Test
    void testComprarPassagem() {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCodigoAeroportuario("Codigo");

        Voo voo = new Voo();
        voo.setAeroportoOrigem(aeroporto);
        voo.setAeroportoDestino(aeroporto);

        Bagagem bagagem = new Bagagem();

        Passageiro passageiro = new Passageiro();
        passageiro.setBagagem(bagagem);

        Classe classe = new Classe();
        classe.setId(1L);
        classe.setVoo(voo);
        classe.setPassageiro(passageiro);

        Passagem passagem = new Passagem();
        passagem.setClasse(classe);

        AeroportoDTO aeroportoDTO = new AeroportoDTO();
        aeroportoDTO.setCodigoAeroportuario("Codigo");

        VooDTO vooDTO = new VooDTO();
        vooDTO.setAeroportoOrigem(aeroportoDTO);
        vooDTO.setAeroportoDestino(aeroportoDTO);

        BagagemDTO bagagemDTO = new BagagemDTO();

        PassageiroDTO passageiroDTO = new PassageiroDTO();
        passageiroDTO.setBagagens(List.of(bagagemDTO));

        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId(1L);
        classeDTO.setVoo(vooDTO);
        classeDTO.setPassageiro(passageiroDTO);

        PassagemDTO passagemDTO = new PassagemDTO();
        passagemDTO.setClasse(classeDTO);

        when(classeRepository.findById(1L)).thenReturn(Optional.of(classe));

        when(modelMappingAssento.convertToEntity(any(), any())).thenReturn(new Assento());
        when(modelMappingPassagem.convertToEntity(any(), any())).thenReturn(passagem);
        when(modelMappingPassageiro.convertToEntity(any(), any())).thenReturn(passageiro);
        when(modelMappingBagagem.convertToEntity(any(), any())).thenReturn(bagagem);

        String result = passagemService.comprarPassagem(passagemDTO);
        Assertions.assertEquals("Compra efetuada com sucesso!", result);
    }

    @Test
    void testCancelarPassagem() {
        VooDTO vooDTO = new VooDTO();
        vooDTO.setId(1L);

        ClasseDTO classeDTO = new ClasseDTO();
        classeDTO.setId(1L);
        classeDTO.setVoo(vooDTO);

        PassagemDTO passagemDTO = new PassagemDTO();
        passagemDTO.setId(1L);
        passagemDTO.setClasse(classeDTO);

        String result = passagemService.cancelarPassagem(passagemDTO);
        Assertions.assertEquals("Passagem cancelada com sucesso!", result);
    }
}