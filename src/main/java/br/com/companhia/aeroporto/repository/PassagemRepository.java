package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {

}