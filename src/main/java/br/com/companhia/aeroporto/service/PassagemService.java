package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.dto.*;
import br.com.companhia.aeroporto.exception.DataIntegrityViolationException;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PassagemService {

    public static final Random RANDOM = new Random();

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private BagagemRepository bagagemRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private ModelMapping<Assento, AssentoDTO> modelMappingAssento;

    @Autowired
    private ModelMapping<Passagem, PassagemDTO> modelMappingPassagem;

    @Autowired
    private ModelMapping<Passageiro, PassageiroDTO> modelMappingPassageiro;

    @Autowired
    private ModelMapping<Bagagem, BagagemDTO> modelMappingBagagem;

    public List<PassagemDTO> findAll() {
        return modelMappingPassagem.convertToDtoList(passagemRepository.findAll(), PassagemDTO.class);
    }

    public List<PassagemDTO> findAllByUuidUsuario(String uuidUsuario) {
        List<Passagem> passagemList = passagemRepository.findAllByUuidUsuario(uuidUsuario);
        List<PassagemDTO> passagemDTOList = modelMappingPassagem.convertToDtoList(passagemList, PassagemDTO.class);
        passagemDTOList.forEach(p -> {
            Assento assento = assentoRepository.findAssentoByClasseId(p.getClasse().getId());
            p.getClasse().setAssentos(modelMappingAssento.convertToDto(assento, AssentoDTO.class));

            Passageiro passageiro = passagemList.stream().filter(f -> f.getClasse().getId().equals(p.getClasse().getId())).toList().get(0).getClasse().getPassageiro();
            if (Objects.isNull(p.getClasse().getPassageiro()) && Objects.nonNull(passageiro)) {
                p.getClasse().setPassageiro(modelMappingPassageiro.convertToDto(passageiro, PassageiroDTO.class));
            }

            if (Objects.nonNull(p.getClasse().getPassageiro())) {
                List<Bagagem> bagagemList = bagagemRepository.findBagagensByPassageiroId(p.getClasse().getPassageiro().getId());
                p.getClasse().getPassageiro().setBagagens(modelMappingBagagem.convertToDtoList(bagagemList, BagagemDTO.class));
            }
        });

        return passagemDTOList;
    }

    public String comprarPassagem(PassagemDTO passagemDTO) {
        String codigoAeroportuario = getCodigoAeroportuario(passagemDTO);
        String codigoUnicoPassagem = geraCodigoUnicoPassagem(codigoAeroportuario);

        salvarBagagensEpassageiros(passagemDTO, (codigoUnicoPassagem + "BGA"));

        Passagem passagem = mountPassagemEntity(passagemDTO, codigoUnicoPassagem);

        try {
            passagemRepository.save(passagem);
            return "Compra efetuada com sucesso!";
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    private void salvarBagagensEpassageiros(PassagemDTO passagemDTO, String codigoUnicoBagagem) {
        PassageiroDTO passageiroDTO = passagemDTO.getClasse().getPassageiro();

        passageiroDTO.getBagagens().forEach(bagagemDTO -> {
           bagagemDTO.setNumeroIdentificacao(codigoUnicoBagagem);
           Bagagem bagagem = modelMappingBagagem.convertToEntity(bagagemDTO, Bagagem.class);
           bagagemRepository.save(bagagem);
           Passageiro passageiro = passageiroRepository.save(mountPassageiroEntity(passageiroDTO, bagagem));
           classeRepository.updateAssentoVooDaPassagemComprada(passageiro, passagemDTO.getClasse().getId(),
                                                               passagemDTO.getClasse().getVoo().getId());
        });
    }

    public String cancelarPassagem(PassagemDTO passagemDTO) {
        try {
            passagemRepository.cancelarPassagem(passagemDTO.getId());
            classeRepository.updateAssentoVooDaPassagemComprada(null, passagemDTO.getClasse().getId(),
                                                                passagemDTO.getClasse().getVoo().getId());
            return "Passagem cancelada com sucesso!";
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    private Passageiro mountPassageiroEntity(PassageiroDTO passageiroDTO, Bagagem bagagem) {
        Passageiro passageiro = new Passageiro();
        passageiro.setRg(passageiroDTO.getRg());
        passageiro.setCpf(passageiroDTO.getCpf());
        passageiro.setBagagem(bagagem);
        passageiro.setTelefone(passageiroDTO.getTelefone());
        passageiro.setPassaporte(passageiroDTO.getPassaporte());
        passageiro.setNomeCompleto(passageiroDTO.getNomeCompleto());
        passageiro.setDataNascimento(passageiroDTO.getDataNascimento());
        passageiro.setContatoEmergencia(passageiroDTO.getContatoEmergencia());

        return passageiro;
    }

    private Passagem mountPassagemEntity(PassagemDTO passagemDTO, String codigoUnicoPassagem) {
        Passagem passagem = new Passagem();

        Classe classe = classeRepository.findById(passagemDTO.getClasse().getId())
                                        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

        passagem.setClasse(classe);
        passagem.setStatus("Ativa");
        passagem.setValor(passagemDTO.getValor());
        passagem.setDataHoraVoo(LocalDateTime.now());
        passagem.setUuidUsuario(passagemDTO.getUuidUsuario());
        passagem.setPortaoEmbarque(passagem.getClasse().getVoo().getPortaoEmbarque());
        passagem.setNumeroIdentificacao(codigoUnicoPassagem);

        return passagem;
    }

    private String getCodigoAeroportuario(PassagemDTO passagemDTO) {
        return passagemDTO.getClasse().getVoo().getAeroportoDestino().getCodigoAeroportuario();
    }

    private String geraCodigoUnicoPassagem(String ae) {
        return new StringBuilder(ae).reverse().toString() +
                LocalDateTime.now().getHour() +
                String.valueOf(LocalDateTime.now().getYear()).charAt(0) +
                LocalDateTime.now().getDayOfMonth() +
                LocalDateTime.now().getMonthValue() +
                String.valueOf(LocalDateTime.now().getYear()).substring(2, 4) +
                LocalDateTime.now().getMinute() +
                LocalDateTime.now().getSecond();
    }
}
