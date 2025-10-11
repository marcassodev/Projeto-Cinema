package com.projeto_casaCultural.cinema.service;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.AnaliseRepository;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    
    public List<Analise> listarTodas() {
        return analiseRepository.findAll();
    }

    
    public Optional<Analise> buscarPorId(Long id) {
        return analiseRepository.findById(id);
    }

  
    public List<Analise> listarPorFilme(Long filmeId) {
        Optional<Filme> filme = filmeRepository.findById(filmeId);
        return filme.map(analiseRepository::findByFilme).orElse(null);
    }

   
    public Analise salvarAnalise(Analise analise) {
        // Exemplo de regra: impedir nota fora do intervalo
        if (analise.getNota() < 0 || analise.getNota() > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }

        return analiseRepository.save(analise);
    }

    
    public Analise atualizarAnalise(Long id, Analise analiseAtualizada) {
        return analiseRepository.findById(id).map(analise -> {
            analise.setComentario(analiseAtualizada.getComentario());
            analise.setNota(analiseAtualizada.getNota());
            return analiseRepository.save(analise);
        }).orElse(null);
    }

    
    public boolean deletarAnalise(Long id) {
        if (analiseRepository.existsById(id)) {
            analiseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
