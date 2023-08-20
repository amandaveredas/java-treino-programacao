package br.com.coruja.application.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.coruja.application.model.Aluno;
import br.com.coruja.application.repository.AlunoRepository;

@RestController
@RequestMapping ("/api/alunos")
public class AlunoController {   

    @Autowired
    private AlunoRepository alunoRepository;

    //salvar
    @PostMapping
    public ResponseEntity<Aluno> salvar (@RequestBody Aluno aluno){

        alunoRepository.save(aluno);
        return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    }



    //buscarPeloId
    // @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Aluno> buscarPeloId (@PathVariable ("id") Long id){

    //     Optional<Aluno> aluno = alunoRepository.findById(id);

    //     if (aluno.isPresent()){
    //         return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
    //     }else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    // }

    //buscarTodos

    //atualizarPeloId

    //deletarPeloId
    
}
