package br.com.coruja.application.controller;

import java.util.Collection;
import java.util.Optional;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.coruja.application.model.Aluno;
import br.com.coruja.application.repository.AlunoRepository;

@RestController
@RequestMapping (value = "/api/alunos")
public class AlunoController {   

    @Autowired
    private AlunoRepository alunoRepository;

    //salvar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> salvar (@RequestBody Aluno aluno){

        alunoRepository.save(aluno);
        return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    }


    //buscarPeloId
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> buscarPeloId (@PathVariable ("id") long id){

        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isPresent()){
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //buscarTodos
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Aluno>> buscarTodos (){
        return new ResponseEntity<>(alunoRepository.findAll(), HttpStatus.OK);
    }


    //atualizarPeloId
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> atualizarPeloId (@PathVariable("id") long id, @RequestBody Aluno alunoAtualizado){
        
        Optional<Aluno> aluno = alunoRepository.findById(id);
        
        if(aluno.isPresent()){
            alunoRepository.save(alunoAtualizado);
            return new ResponseEntity<Aluno>(alunoAtualizado, HttpStatus.OK);            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //deletarPeloId
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> deletarPeloId (@PathVariable("id") long id){

        alunoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
