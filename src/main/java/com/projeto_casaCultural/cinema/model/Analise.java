package com.projeto_casaCultural.cinema.model;

public class Analise {
        Long id;
        String comentario;
        double nota;
        Filme filme;

    public Analise() {}

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    } 

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Analise(Long id, Filme filme, String comentario, double nota) {
        this.id = id;
        this.filme = filme;
        this.comentario = comentario;
        this.nota = nota;
    }

    
        
        
}
