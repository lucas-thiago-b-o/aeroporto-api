package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

    List<Aeroporto> findAeroportoByCidade_Id(Long id);

    @Query(value = "SELECT ae FROM Aeroporto ae WHERE ae.cidade.uf.id = ?1")
    List<Aeroporto> findAeroportoByUfId(Long id);

}
