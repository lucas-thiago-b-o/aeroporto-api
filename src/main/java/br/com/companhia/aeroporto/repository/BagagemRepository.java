package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Bagagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagagemRepository extends JpaRepository<Bagagem, Long> {

}