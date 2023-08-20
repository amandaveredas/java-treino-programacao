package br.com.coruja.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.application.model.Aluno;
import br.com.coruja.application.repository.AlunoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {
    
    @Autowired
    protected WebTestClient web;

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    void setUp(){
        
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
        alunoRepository.deleteAll();

    }

    @Test
    public void deveSalvarAluno_quandoAlunoFornecido(){

        Aluno aluno = new Aluno("Amanda", "amanda@gmail.com");

        web.post().uri("/api/alunos")
                    .body(BodyInserters.fromValue(aluno))
                    .exchange()
                    .expectStatus().isCreated().expectBody(Aluno.class)
                    .value(c -> assertTrue(c.getId()>0))
                    .value(c -> assertEquals("Amanda", c.getNome()))
                    .value(c -> assertEquals("amanda@gmail.com", c.getEmail()));
    }
}
