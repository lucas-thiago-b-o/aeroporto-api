package br.com.companhia.aeroporto.config;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DatabaseInstanceInitializer {

    public static final Random random = new Random();

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
        ufRepository.saveAll(List.of(getUfBahia(), getUfRioDeJaneiro(), getUfAcre()));

        cidadeRepository.saveAll(List.of(getCidadeSalvador(), getCidadeRioDeJaneiro(), getCidadePortoSeguro(), getCidadeCruzeiroDoSul()));

        aeroportoRepository.saveAll(List.of(getAeroportoSalvador(), getAeroportoRioDeJaneiroSantosDumont(), getAeroportoRioDeJaneiroGaleao(),
                                            getAeroportoPortoSeguro(), getAeroportoCruzeiroDoSul()));

        bagagemRepository.save(getBagagem(false));

        passageiroRepository.save(getPassageiro());

        passagemRepository.save(getPassagem());
    }

    public Uf getUfBahia() {
        Uf bahia = new Uf();

        bahia.setId(1L);
        bahia.setNome("Bahia");

        return bahia;
    }

    public Uf getUfAcre() {
        Uf acre = new Uf();

        acre.setId(3L);
        acre.setNome("Acre");

        return acre;
    }

    public Uf getUfRioDeJaneiro() {
        Uf rj = new Uf();

        rj.setId(2L);
        rj.setNome("Rio de Janeiro");

        return rj;
    }

    public Cidade getCidadeSalvador() {
        Cidade salvador = new Cidade();

        salvador.setId(1L);
        salvador.setNome("Salvador");
        salvador.setUf(getUfBahia());

        return salvador;
    }

    public Cidade getCidadePortoSeguro() {
        Cidade portoSeguro = new Cidade();

        portoSeguro.setId(3L);
        portoSeguro.setNome("Porto Seguro");
        portoSeguro.setUf(getUfBahia());

        return portoSeguro;
    }

    public Cidade getCidadeRioDeJaneiro() {
        Cidade riodejaneiro = new Cidade();

        riodejaneiro.setId(2L);
        riodejaneiro.setNome("Rio de Janeiro");
        riodejaneiro.setUf(getUfRioDeJaneiro());

        return riodejaneiro;
    }

    public Cidade getCidadeCruzeiroDoSul() {
        Cidade cruzeiroDoSul = new Cidade();

        cruzeiroDoSul.setId(4L);
        cruzeiroDoSul.setNome("Cruzeiro do Sul");
        cruzeiroDoSul.setUf(getUfAcre());

        return cruzeiroDoSul;
    }

    public Aeroporto getAeroportoSalvador() {
        Aeroporto aeroportoSalvador = new Aeroporto();

        aeroportoSalvador.setId(1L);
        aeroportoSalvador.setCidade(getCidadeSalvador());
        aeroportoSalvador.setNome("Aeroporto Internacional Dep. Luís Eduardo Magalhães");
        aeroportoSalvador.setCodigoAeroportuario("SSA");

        return aeroportoSalvador;
    }

    public Aeroporto getAeroportoPortoSeguro() {
        Aeroporto aeroportoPortoSeguro = new Aeroporto();

        aeroportoPortoSeguro.setId(4L);
        aeroportoPortoSeguro.setCidade(getCidadePortoSeguro());
        aeroportoPortoSeguro.setNome("Aeroporto de Porto Seguro");
        aeroportoPortoSeguro.setCodigoAeroportuario("BPS");

        return aeroportoPortoSeguro;
    }

    public Aeroporto getAeroportoCruzeiroDoSul() {
        Aeroporto aeroportoCruzeiroDoSul = new Aeroporto();

        aeroportoCruzeiroDoSul.setId(5L);
        aeroportoCruzeiroDoSul.setCidade(getCidadeCruzeiroDoSul());
        aeroportoCruzeiroDoSul.setNome("Aeroporto Internacional de Cruzeiro do Sul");
        aeroportoCruzeiroDoSul.setCodigoAeroportuario("CZS");

        return aeroportoCruzeiroDoSul;
    }

    public Aeroporto getAeroportoRioDeJaneiroSantosDumont() {
        Aeroporto aeroportoRioDeJaneiroSantosDumont = new Aeroporto();

        aeroportoRioDeJaneiroSantosDumont.setId(2L);
        aeroportoRioDeJaneiroSantosDumont.setCidade(getCidadeRioDeJaneiro());
        aeroportoRioDeJaneiroSantosDumont.setNome("Aeroporto do Rio de Janeiro-Santos Dumont");
        aeroportoRioDeJaneiroSantosDumont.setCodigoAeroportuario("SDU");

        return aeroportoRioDeJaneiroSantosDumont;
    }

    public Aeroporto getAeroportoRioDeJaneiroGaleao() {
        Aeroporto aeroportoRioDeJaneiroGaleao = new Aeroporto();


        aeroportoRioDeJaneiroGaleao.setId(3L);
        aeroportoRioDeJaneiroGaleao.setCidade(getCidadeRioDeJaneiro());
        aeroportoRioDeJaneiroGaleao.setNome("Aeroporto Internacional do Rio de Janeiro-Galeão");
        aeroportoRioDeJaneiroGaleao.setCodigoAeroportuario("GIG");

        return aeroportoRioDeJaneiroGaleao;
    }

    public Passageiro getPassageiro() {
        Passageiro passageiro1 = new Passageiro();

        passageiro1.setId(1L);
        passageiro1.setDataNascimento(LocalDateTime.now());
        passageiro1.setCpf(13140923759L);
        passageiro1.setNomeCompleto("Lucas");
        passageiro1.setRg(248142317L);
        passageiro1.setPassaporte(12345L);
        passageiro1.setContatoEmergencia(21912345678L);
        passageiro1.setTelefone(21969487457L);
        passageiro1.setBagagem(getBagagem(false));

        return passageiro1;
    }

    public Bagagem getBagagem(Boolean isDespachado) {
        Bagagem bagagem1 = new Bagagem();

        bagagem1.setId(1L);
        bagagem1.setNumeroIdentificacao("UDS22223012411BGA");
        bagagem1.setIsDespachada(isDespachado);

        return bagagem1;
    }

    public Passagem getPassagem() {
        Passagem passagem = new Passagem();

        passagem.setId(1L);
        passagem.setVoo(getVoo());
        passagem.setDataHoraVoo(LocalDateTime.now());
        passagem.setNumeroIdentificacao("UDS22223012411");
        passagem.setPortaoEmbarque("Portao");
        passagem.setValor(geraValorVooEpassagem());

        return passagem;
    }

    public Voo getVoo() {
        Voo voo = new Voo();
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(0);

        for (char letra = 'A'; letra <= 'Z'; letra++) {
            for (int i = 1; i <= 26; i++) {
                long id = atomicLong.incrementAndGet();

                Assento assento = saveAndGetAssento(id, letra, i);
                Classe classe = saveAndGetClasse(id, i, assento);
                voo = mountVoo(id, getAeroportoSalvador(), getAeroportoRioDeJaneiroSantosDumont(),
                                   getAeroportoPortoSeguro(), getAeroportoCruzeiroDoSul(),
                               classe);

                vooRepository.save(voo);
            }
        }

        return voo;
    }

    private Assento saveAndGetAssento(long id, char letra, int i) {
        Assento assento = new Assento();
        assento.setId(id);
        assento.setNome(String.valueOf(letra) + Integer.toUnsignedLong(i));
        assentoRepository.save(assento);
        return assento;
    }

    private Classe saveAndGetClasse(long id, int i, Assento assento) {
        Classe classe = new Classe();
        classe.setId(id);
        classe.setAssento(assento);
        classe.setNome(i <= 11 ? "Primeira Classe" : "Classe Econômica");
        classeRepository.save(classe);
        return classe;
    }

    private Voo mountVoo(long id, Aeroporto aeroportoSalvador, Aeroporto aeroportoRioDeJaneiroSantosDumont,
                                  Aeroporto aeroportoPortoSeguro, Aeroporto aeroportoCruzeiroDoSul,
                         Classe classe) {
        Voo voo = new Voo();
        voo.setId(id);

        if (id <= 169) {
            voo.setAeroportoOrigem(aeroportoSalvador);
            voo.setAeroportoDestino(aeroportoRioDeJaneiroSantosDumont);
        } else if (id >= 338 && id < 507) {
            voo.setAeroportoOrigem(aeroportoPortoSeguro);
            voo.setAeroportoDestino(aeroportoRioDeJaneiroSantosDumont);
        } else if (id >= 507 && id < 591) {
            voo.setAeroportoOrigem(aeroportoCruzeiroDoSul);
            voo.setAeroportoDestino(aeroportoRioDeJaneiroSantosDumont);
        } else {
            voo.setAeroportoOrigem(aeroportoRioDeJaneiroSantosDumont);
            voo.setAeroportoDestino(aeroportoSalvador);
        }

        voo.setDataHoraChegada(LocalDateTime.now());
        voo.setDataHoraMarcado(LocalDateTime.now().plusHours(24));
        voo.setDataHoraPartida(LocalDateTime.now());
        voo.setDataHoraPrevisao(LocalDateTime.now());
        voo.setNome("Voo " + id);
        voo.setClasse(classe);
        voo.setStatus("Programado");
        voo.setValor(geraValorVooEpassagem());
        voo.setPassageiro(id == 1 ? getPassageiro() : null);

        return voo;
    }

    public Long geraValorVooEpassagem() {
        int valorAleatorio = random.nextInt(39001) + 1000;

        return Integer.toUnsignedLong(valorAleatorio);
    }
}
