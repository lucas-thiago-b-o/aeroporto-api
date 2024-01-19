package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

}
