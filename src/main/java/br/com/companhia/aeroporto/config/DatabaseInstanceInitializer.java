package br.com.companhia.aeroporto.config;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseInstanceInitializer {

    @Autowired
    AeroportoRepository aeroportoRepository;

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private BagagemRepository bagagemRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private UfRepository ufRepository;

    @Autowired
    private VooRepository vooRepository;

    public void instanciaDadosNaBase() {
        Cidade salvador = new Cidade();
        Cidade riodejaneiro = new Cidade();
        Uf bahia = new Uf();
        Uf rj = new Uf();
        Aeroporto aeroportoSalvador = new Aeroporto();
        Aeroporto aeroportoRioDeJaneiro = new Aeroporto();
        Voo vooSalvador1 = new Voo();
        Passageiro passageiro1 = new Passageiro();
        Classe classe11 = new Classe();
        Bagagem bagagem1 = new Bagagem();
        Assento assento11 = new Assento();
        Passagem passagem = new Passagem();

        bahia.setId(1L);
        bahia.setNome("Bahia");

        rj.setId(2L);
        rj.setNome("Rio de Janeiro");

        salvador.setId(1L);
        salvador.setNome("Salvador");
        salvador.setUf(bahia);

        riodejaneiro.setId(2L);
        riodejaneiro.setNome("Rio de Janeiro");
        riodejaneiro.setUf(rj);

        aeroportoSalvador.setId(1L);
        aeroportoSalvador.setCidade(salvador);
        aeroportoSalvador.setNome("Aeroporto Internacional Dep. Luís Eduardo Magalhães");
        aeroportoSalvador.setCodigoAeroportuario("SSA");

        aeroportoRioDeJaneiro.setId(2L);
        aeroportoRioDeJaneiro.setCidade(riodejaneiro);
        aeroportoRioDeJaneiro.setNome("Aeroporto do Rio de Janeiro-Santos Dumont");
        aeroportoRioDeJaneiro.setCodigoAeroportuario("SDU");

        assento11.setId(1L);
        assento11.setNome("F5");

        classe11.setId(1L);
        classe11.setNome("Primeira Classe");
        classe11.setAssento(assento11);

        bagagem1.setId(1L);
        bagagem1.setNumeroIdentificacao(12345L);
        bagagem1.setIsDespachada(false);

        passageiro1.setId(1L);
        passageiro1.setDataNascimento(LocalDateTime.now());
        passageiro1.setCpf(13140923759L);
        passageiro1.setNomeCompleto("Lucas");
        passageiro1.setRg(248142317L);
        passageiro1.setPassaporte(12345L);
        passageiro1.setContatoEmergencia(21912345678L);
        passageiro1.setTelefone(21969487457L);
        passageiro1.setBagagem(bagagem1);

        vooSalvador1.setId(1L);
        vooSalvador1.setAeroportoOrigem(aeroportoSalvador);
        vooSalvador1.setAeroportoDestino(aeroportoRioDeJaneiro);
        vooSalvador1.setDataHoraChegada(LocalDateTime.now());
        vooSalvador1.setDataHoraMarcado(LocalDateTime.now());
        vooSalvador1.setDataHoraPartida(LocalDateTime.now());
        vooSalvador1.setDataHoraPrevisao(LocalDateTime.now());
        vooSalvador1.setNome("Voo 1");
        vooSalvador1.setClasse(classe11);
        vooSalvador1.setPassageiro(passageiro1);

        passagem.setId(1L);
        passagem.setVoo(vooSalvador1);
        passagem.setDataHoraVoo(LocalDateTime.now());
        passagem.setNumeroIdentificacao(12345L);
        passagem.setPortaoEmbarque("Portao");
        passagem.setClasse(classe11);
        passagem.setValor(12345L);

        ufRepository.save(bahia);
        ufRepository.save(rj);
        cidadeRepository.save(salvador);
        cidadeRepository.save(riodejaneiro);
        aeroportoRepository.save(aeroportoSalvador);
        aeroportoRepository.save(aeroportoRioDeJaneiro);
        assentoRepository.save(assento11);
        classeRepository.save(classe11);
        bagagemRepository.save(bagagem1);
        passageiroRepository.save(passageiro1);
        vooRepository.save(vooSalvador1);
        passagemRepository.save(passagem);
    }
}
