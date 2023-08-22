package br.com.coruja.application.repository;
import br.com.coruja.application.model.Turma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma,Long>{
    
}
