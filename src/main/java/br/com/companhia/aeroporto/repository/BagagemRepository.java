package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Bagagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagagemRepository extends JpaRepository<Bagagem, Long> {

    @Query(value = "SELECT p.bagagem FROM Passageiro p WHERE p.id = ?1")
    List<Bagagem> findBagagensByPassageiroId(Long idPassageiro);
}