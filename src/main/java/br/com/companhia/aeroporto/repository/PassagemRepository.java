package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Passagem p SET p.status = 'Cancelada' WHERE p.id = ?1")
    void cancelarPassagem(Long idPassagem);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Passagem p SET p.status = 'Cancelada' WHERE p.classe.voo.id = ?1")
    void updatePassagemByVooCancelado(Long vooId);

    List<Passagem> findAllByUuidUsuario(String uuid);
}