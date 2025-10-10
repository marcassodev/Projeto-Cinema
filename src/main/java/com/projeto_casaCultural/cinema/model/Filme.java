package com.projeto_casaCultural.cinema.model;

public class Filme {
        int anoLancamento;
        String titulo, sinopse, genero;
        Long id;

    public Filme() {}    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Filme(int anoLancamento, String titulo, String sinopse, String genero, Long id) {
        this.anoLancamento = anoLancamento;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.id = id;
    }
        
}
