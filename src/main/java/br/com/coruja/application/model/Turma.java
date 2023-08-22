package br.com.coruja.application.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Turma {

    @Id
    @GeneratedValue
    long id;

    int serie;

    Modalidade modalidade;

    // @OneToMany
    // @JoinColumn(name = "turma_id")
    // List<Aluno> alunos;

    public Turma(int serie, Modalidade modalidade) {
        this.serie = serie;
        this.modalidade = modalidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    // public List<Aluno> getAlunos() {
    //     return alunos;
    // }

    // public void setAlunos(List<Aluno> alunos) {
    //     this.alunos = alunos;
    // }

    

    
    
}
