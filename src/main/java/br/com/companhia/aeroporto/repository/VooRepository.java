package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Passageiro;
import br.com.companhia.aeroporto.domain.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

    @Query(value = "SELECT v.* FROM voo as v " +
                   " JOIN aeroporto AS ae ON v.aeroporto_origem_id = ae.id " +
                   " WHERE ae.id = ?1 AND v.status = 'Programado'" +
                   " AND v.data_hora_marcado > (NOW() + INTERVAL 5 HOUR) " +
                   "  AND v.passageiro_id IS NULL", nativeQuery = true)
    List<Voo> findAllByAeroportoOrigemId(Long id);

    @Query(value = "SELECT v.* FROM voo as v " +
                   " JOIN aeroporto AS ae ON v.aeroporto_origem_id = ae.id " +
                   " WHERE ae.cidade_id = ?1 AND v.status = 'Programado'" +
                   " AND v.data_hora_marcado > (NOW() + INTERVAL 5 HOUR) " +
                   "  AND v.passageiro_id IS NULL", nativeQuery = true)
    List<Voo> findAllByCidadeId(Long id);

    @Query(value = "SELECT v.* FROM voo as v " +
                   " JOIN aeroporto AS aeo ON v.aeroporto_origem_id = aeo.id " +
                   " JOIN aeroporto AS aed ON v.aeroporto_destino_id = aed.id " +
                   " WHERE aeo.cidade_id = ?1 AND aed.cidade_id = ?2 " +
                   " AND v.status = 'Programado' " +
                   " AND v.data_hora_marcado > (NOW() + INTERVAL 5 HOUR) " +
                   "  AND v.passageiro_id IS NULL", nativeQuery = true)
    List<Voo> findAllByCidadesIds(Long idOrigem, Long idDestino);

    @Query(value = "SELECT v.* FROM voo as v " +
                   " JOIN aeroporto AS ae ON v.aeroporto_origem_id = ae.id " +
                   " JOIN cidade AS c ON c.id = ae.cidade_id " +
                   " WHERE c.uf_id = ?1 AND v.status = 'Programado'" +
                   " AND v.data_hora_marcado > (NOW() + INTERVAL 5 HOUR) " +
                   "  AND v.passageiro_id IS NULL", nativeQuery = true)
    List<Voo> findAllByUfId(Long id);

    /*@Query(value = "UPDATE Voo v SET v.passageiro = ?1 WHERE v.classe.id = ?2 AND v.id = ?3")
    void updateAssentoVooDaPassagemComprada(Passageiro passageiro, Long classeId, Long vooId);*/

}
