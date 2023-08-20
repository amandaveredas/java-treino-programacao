package br.com.coruja.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.application.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    public Optional<Aluno> findById(Long id);
    

}
