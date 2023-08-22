package br.com.coruja.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.application.model.Modalidade;
import br.com.coruja.application.model.Turma;
import br.com.coruja.application.repository.TurmaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TurmaControllerTest {

    @Autowired
    protected WebTestClient web;

    @Autowired
    private TurmaRepository repository;

    @BeforeEach
    void setUp(){
            repository.deleteAll();
    }

    @Test
    public void deveSalvarTurma (){

        Turma turma = new Turma(8,Modalidade.FUNDAMENTAL2);

        web.post().uri("/api/turmas")
                    .accept(MediaType.ALL)
                    .body(BodyInserters.fromValue(turma))    
                    .exchange()
                    .expectStatus().isCreated().expectBody(Turma.class)
                    .value(c ->assertTrue(c.getId() > 0))
                    .value(c ->assertEquals(8, c.getSerie()));
    }


    
}
