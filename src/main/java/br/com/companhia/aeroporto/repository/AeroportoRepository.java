package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

}
