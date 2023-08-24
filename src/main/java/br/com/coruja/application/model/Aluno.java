package br.com.coruja.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    long id;

    String nome;

    String email;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    Turma turma;

    public Aluno() {
    }
    
    

    public Aluno(long id, String nome, String email, Turma turma) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.turma = turma;
    }




    public Aluno(String nome, String email, Turma turma) {
        this.nome = nome;
        this.email = email;
        this.turma = turma;
    }




    public Aluno(String nome) {
        this.nome = nome;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;    
    }


    public Turma getTurma() {
        return turma;
    }


    public void setTurma(Turma turma) {
        this.turma = turma;
    }   

    
    


}    