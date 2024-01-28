package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Classe;
import br.com.companhia.aeroporto.domain.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Query(value = "UPDATE Classe c SET c.assento.passageiro = ?1 WHERE c.id = ?2 AND c.voo.id = ?3")
    void updateAssentoVooDaPassagemComprada(Passageiro passageiro, Long classeId, Long vooId);

    List<Classe> findAllByVooId(Long id);
}
