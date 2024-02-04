package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Classe;
import br.com.companhia.aeroporto.domain.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Query(value = "SELECT COUNT(*) FROM `classe` WHERE voo_id = ?1 AND passageiro_id IS NOT NULL;", nativeQuery = true)
    Integer pegaQuantPassageirosByVoo(Long idVoo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Classe c SET c.passageiro = ?1 WHERE c.id = ?2 AND c.voo.id = ?3")
    void updateAssentoVooDaPassagemComprada(Passageiro passageiro, Long classeId, Long vooId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Classe c SET c.passageiro = NULL WHERE c.voo.id = ?1")
    void updateAssentosByVooCancelado(Long vooId);

    List<Classe> findAllByVooId(Long id);
}
