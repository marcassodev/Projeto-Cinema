package com.projeto_casaCultural.cinema.service;

import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // Listar todos os filmes
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    // Buscar um filme pelo ID
    public Filme buscarPorId(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        return filme.orElse(null);
    }

    // Salvar ou atualizar um filme
    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    // Deletar filme
    public void deletar(Long id) {
        filmeRepository.deleteById(id);
    }

}
