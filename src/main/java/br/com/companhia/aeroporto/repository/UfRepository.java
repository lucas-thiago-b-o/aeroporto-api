package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

}
