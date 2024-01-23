package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {

    @Query(value = "UPDATE Passagem p SET p.status = 'Cancelada' WHERE p.id = ?1")
    void cancelarPassagem(Long idPassagem);
}