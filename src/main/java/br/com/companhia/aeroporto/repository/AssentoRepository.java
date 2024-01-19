package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {

}
