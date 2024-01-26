package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.dto.*;
import br.com.companhia.aeroporto.exception.DataIntegrityViolationException;
import br.com.companhia.aeroporto.exception.ObjectNotFoundException;
import br.com.companhia.aeroporto.model.ModelMapping;
import br.com.companhia.aeroporto.repository.BagagemRepository;
import br.com.companhia.aeroporto.repository.PassageiroRepository;
import br.com.companhia.aeroporto.repository.PassagemRepository;
import br.com.companhia.aeroporto.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassagemService {

    @Autowired
    private VooRepository vooRepository;

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private BagagemRepository bagagemRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private ModelMapping<Passagem, PassagemDTO> modelMappingPassagem;

    @Autowired
    private ModelMapping<Bagagem, BagagemDTO> modelMappingBagagem;

    public List<PassagemDTO> findAll() {
        return modelMappingPassagem.convertToDtoList(passagemRepository.findAll(), PassagemDTO.class);
    }

    public List<PassagemDTO> findAllByUuidUsuario(String uuidUsuario) {
        return modelMappingPassagem.convertToDtoList(passagemRepository.findAllByUuidUsuario(uuidUsuario), PassagemDTO.class);
    }

    public String comprarPassagem(ComprarPassagemDTO comprarPassagemDTO) {
        String codigoAeroportuario = getCodigoAeroportuario(comprarPassagemDTO);
        String codigoUnicoPassagem = geraCodigoUnicoPassagem(codigoAeroportuario);

        salvarBagagensEpassageiros(comprarPassagemDTO, (codigoUnicoPassagem + "BGA"));

        Passagem passagem = mountPassagemEntity(comprarPassagemDTO, codigoUnicoPassagem);

        try {
            passagemRepository.save(passagem);
            return "Compra efetuada com sucesso!";
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    private void salvarBagagensEpassageiros(ComprarPassagemDTO comprarPassagemDTO, String codigoUnicoBagagem) {
       comprarPassagemDTO.getPassageirosAssentoDTO().forEach(passageiroDTO ->
               passageiroDTO.getPassageiroDTO().getBagagens().forEach(bagagemDTO -> {
                   bagagemDTO.setNumeroIdentificacao(codigoUnicoBagagem);
                   Bagagem bagagem = modelMappingBagagem.convertToEntity(bagagemDTO, Bagagem.class);
                   bagagemRepository.save(bagagem);
                   Passageiro passageiro = mountPassageiroEntity(passageiroDTO.getPassageiroDTO(), bagagem);
                   passageiroRepository.save(passageiro);
                   vooRepository.updateAssentoVooDaPassagemComprada(passageiro, passageiroDTO.getClasseId(),
                                                                    comprarPassagemDTO.getPassagemDTO().getVoo().getId());
               })
       );
    }

    public String cancelarPassagem(PassagemDTO passagemDTO) {
        try {
            passagemRepository.cancelarPassagem(passagemDTO.getId());
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

    private Passagem mountPassagemEntity(ComprarPassagemDTO comprarPassagemDTO, String codigoUnicoPassagem) {
        Passagem passagem = new Passagem();

        Voo voo = vooRepository.findById(comprarPassagemDTO.getPassagemDTO().getVoo().getId())
                 .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

        passagem.setVoo(voo);
        passagem.setStatus("Ativa");
        passagem.setValor(comprarPassagemDTO.getPassagemDTO().getValor());
        passagem.setDataHoraVoo(LocalDateTime.now());
        passagem.setUuidUsuario(comprarPassagemDTO.getPassagemDTO().getUuidUsuario());
        passagem.setPortaoEmbarque(comprarPassagemDTO.getPassagemDTO().getPortaoEmbarque());
        passagem.setNumeroIdentificacao(codigoUnicoPassagem);

        return passagem;
    }

    private String getCodigoAeroportuario(ComprarPassagemDTO comprarPassagemDTO) {
        return comprarPassagemDTO.getPassagemDTO().getVoo().getAeroportoDestino().getCodigoAeroportuario();
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
