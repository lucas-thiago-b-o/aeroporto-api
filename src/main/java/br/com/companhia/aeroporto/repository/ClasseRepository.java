package br.com.companhia.aeroporto.repository;

import br.com.companhia.aeroporto.domain.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
