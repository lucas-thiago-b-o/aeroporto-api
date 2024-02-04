package br.com.companhia.aeroporto.config;

import br.com.companhia.aeroporto.domain.*;
import br.com.companhia.aeroporto.enums.UsuarioRole;
import br.com.companhia.aeroporto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatabaseInstanceInitializer {

    public static final Random RANDOM = new Random();

    protected static final List<Classe> CLASSES = new ArrayList<>();
    protected static final List<Assento> ASSENTOS = new ArrayList<>();
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VooRepository vooRepository;

    public void instanciaDadosNaBase() {
        ufRepository.saveAll(List.of(getUfBahia(), getUfRioDeJaneiro(), getUfAcre()));

        cidadeRepository.saveAll(List.of(getCidadeSalvador(), getCidadeRioDeJaneiro(), getCidadePortoSeguro(), getCidadeCruzeiroDoSul()));

        aeroportoRepository.saveAll(List.of(getAeroportoSalvador(), getAeroportoRioDeJaneiroSantosDumont(), getAeroportoRioDeJaneiroGaleao(),
                                            getAeroportoPortoSeguro(), getAeroportoCruzeiroDoSul()));

        bagagemRepository.save(getBagagem(false));

        passageiroRepository.save(getPassageiro());

        addVoos();

        passagemRepository.save(getPassagem());

        usuarioRepository.save(getUsuarioAdmin());
    }

    public Usuario getUsuarioAdmin() {
        String encryptedPassword = new BCryptPasswordEncoder().encode(UsuarioRole.ADMIN.getRole());

        return new Usuario(UsuarioRole.ADMIN.getRole(), encryptedPassword, UsuarioRole.ADMIN);
    }

    public Uf getUfBahia() {
        Uf bahia = new Uf();

        bahia.setId(1L);
        bahia.setNome("BA");

        return bahia;
    }

    public Uf getUfAcre() {
        Uf acre = new Uf();

        acre.setId(3L);
        acre.setNome("AC");

        return acre;
    }

    public Uf getUfRioDeJaneiro() {
        Uf rj = new Uf();

        rj.setId(2L);
        rj.setNome("RJ");

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
        passageiro1.setCpf("13140923759");
        passageiro1.setNomeCompleto("Lucas");
        passageiro1.setRg("248142317");
        passageiro1.setPassaporte("12345");
        passageiro1.setContatoEmergencia("21912345678");
        passageiro1.setTelefone("21969487457");
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
        passagem.setClasse(CLASSES.get(0));
        passagem.setDataHoraVoo(LocalDateTime.now());
        passagem.setNumeroIdentificacao("UDS22223012411");
        passagem.setPortaoEmbarque(CLASSES.get(0).getVoo().getPortaoEmbarque());
        passagem.setValor(geraValorClasseAssento());

        return passagem;
    }

    public void addVoos() {
        AtomicLong idsGerais = new AtomicLong();
        idsGerais.set(0);

        AtomicInteger intToReset = new AtomicInteger();
        intToReset.set(-1);
        AtomicInteger vooAtual = new AtomicInteger();
        vooAtual.set(0);

        for (int l = 0; l < 4; l++) {

            Aeroporto aeroportoOrigem;
            Aeroporto aeroportoDestino;
            if (l == 0) {
                aeroportoOrigem = getAeroportoSalvador();
                aeroportoDestino = getAeroportoRioDeJaneiroSantosDumont();
            } else if (l == 1) {
                aeroportoOrigem = getAeroportoPortoSeguro();
                aeroportoDestino = getAeroportoRioDeJaneiroSantosDumont();
            } else if (l == 2) {
                aeroportoOrigem = getAeroportoCruzeiroDoSul();
                aeroportoDestino = getAeroportoRioDeJaneiroSantosDumont();
            } else {
                aeroportoOrigem = getAeroportoRioDeJaneiroSantosDumont();
                aeroportoDestino = getAeroportoSalvador();
            }

            vooAtual.getAndIncrement();

            addClasses(vooAtual.get(), idsGerais, intToReset, aeroportoOrigem, aeroportoDestino);

            classeRepository.saveAll(CLASSES);
        }
    }

    private void addClasses(int vooAtual, AtomicLong idsGerais, AtomicInteger intToReset, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {
        Voo voo = vooRepository.save(mountVoo(vooAtual, aeroportoOrigem, aeroportoDestino));

        for (char letra = 'A'; letra <= 'Z'; letra++) {
            for (int i = 1; i <= 26; i++) {
                long id = idsGerais.incrementAndGet();
                int idClasse = intToReset.incrementAndGet();

                if (id <= 676) {
                    ASSENTOS.add(saveAndGetAssento(id, letra, i));
                } else if (idClasse == 676) {
                    intToReset.set(-1);
                    idClasse = intToReset.incrementAndGet();
                }

                CLASSES.add(saveAndGetClasse(id, i, ASSENTOS.get(idClasse), voo));
            }
        }
    }

    private Assento saveAndGetAssento(long id, char letra, int i) {
        Assento assento = new Assento();
        assento.setId(id);
        assento.setNome(String.valueOf(letra) + Integer.toUnsignedLong(i));
        assentoRepository.save(assento);
        return assento;
    }

    private Classe saveAndGetClasse(long id, int i, Assento assento, Voo voo) {
        Classe classe = new Classe();
        classe.setId(id);
        classe.setAssento(assento);
        classe.setNome(i <= 13 ? "Primeira Classe" : "Classe Econômica");
        classe.setVoo(voo);
        classe.setPassageiro(id == 1 ? getPassageiro() : null);
        classe.setValor(geraValorClasseAssento());
        classeRepository.save(classe);
        return classe;
    }

    private Voo mountVoo(long id, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {
        Voo voo = new Voo();
        voo.setId(id);

        voo.setAeroportoOrigem(aeroportoOrigem);
        voo.setAeroportoDestino(aeroportoDestino);

        voo.setDataHoraChegada(LocalDateTime.now());
        voo.setDataHoraMarcado(LocalDateTime.now().plusHours(12));
        voo.setDataHoraPartida(LocalDateTime.now().plusHours(13));
        voo.setDataHoraPrevisao(LocalDateTime.now().plusHours(24));
        voo.setNome("Voo " + id);
        voo.setStatus("Programado");
        voo.setPortaoEmbarque(geraPortaoEmbarque());

        return voo;
    }

    public String geraPortaoEmbarque() {
        String letras = IntStream.range(0, 2)
                .mapToObj(i -> String.valueOf((char) (RANDOM.nextInt(26) + 'A')))
                .collect(Collectors.joining());

        String numeros = IntStream.range(0, 4)
                .mapToObj(i -> String.valueOf(RANDOM.nextInt(10)))
                .collect(Collectors.joining());

        return letras.concat(numeros);
    }

    public Long geraValorClasseAssento() {
        int valorAleatorio = RANDOM.nextInt(39001) + 1000;

        return Integer.toUnsignedLong(valorAleatorio);
    }
}
