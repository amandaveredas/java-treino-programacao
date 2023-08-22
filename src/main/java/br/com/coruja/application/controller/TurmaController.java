package br.com.coruja.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.application.model.Turma;
import br.com.coruja.application.repository.TurmaRepository;

@RestController
@RequestMapping(value = "/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Turma> salvarTurma (@RequestBody Turma turma){

        repository.save(turma);
        return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);

    }
}
