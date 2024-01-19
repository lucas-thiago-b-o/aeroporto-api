package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

}
