package br.com.coruja.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
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
    public void setUp(){
        
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
        
        /*ATENÇÃO, OS TESTES NÃO ESTÃO FUNCIONANDO QUANDO RODA A CLASSE INTEIRA
         * PRECISO ENTENDER COMO FUNCIONA ESSE BANCO
         */
        
        
        //alunoRepository.deleteAll();

    }

    @Test
    @Order(5)
    public void deveListarAlunos(){
        salvarAluno();
        salvarAluno();
        salvarAluno();

        web.get().uri("/api/alunos")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus().isOk().expectBodyList(Aluno.class)
                .hasSize(3);
    }

    @Test
    @Order(1)
    public void deveSalvarAluno_quandoAlunoFornecido(){

        Aluno aluno = new Aluno("Amanda");

        web.post().uri("/api/alunos")
                    .accept(MediaType.ALL)
                    .body(BodyInserters.fromValue(aluno))                  
                    .exchange()
                    .expectStatus().isCreated().expectBody(Aluno.class)
                    .value(c -> assertTrue(c.getId()>0))
                    .value(c -> assertEquals("Amanda", c.getNome()));
                    
    }

    @Test
    @Order(3)
    public void deveBuscarPeloId_quandoIdExistir(){
        salvarAluno();
     
        web.get().uri("/api/alunos/1")
                    .accept(org.springframework.http.MediaType.ALL)        
                    .exchange()
                    .expectStatus().isOk().expectBody(Aluno.class);

    }


    @Test
    @Order(2)
    public void deveAtualizarAluno_quandoIdExistir(){

        salvarAluno();

        Aluno alunoAtualizado = new Aluno("Amanda Atualizado");
        web.put().uri("api/alunos/1")
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(alunoAtualizado))
                .exchange()
                .expectStatus().isOk().expectBody(Aluno.class)
                .value(c -> assertEquals("Amanda Atualizado", c.getNome()));
        
    }

    @Test
    @Order(4)
    public void deveDeletarPeloId_quandoIdExistir(){

        salvarAluno();

       web.delete().uri("/api/alunos/1")
                    .accept(org.springframework.http.MediaType.ALL)        
                    .exchange()
                    .expectStatus().isOk();

    } 
    

    private void salvarAluno(){
        Aluno aluno = new Aluno("Amanda");

        web.post().uri("/api/alunos")
                    .accept(MediaType.ALL)
                    .body(BodyInserters.fromValue(aluno))                  
                    .exchange()
                    .expectStatus().isCreated().expectBody(Aluno.class)
                    .value(c -> assertTrue(c.getId()>0))
                    .value(c -> assertEquals("Amanda", c.getNome()));

    }
}
