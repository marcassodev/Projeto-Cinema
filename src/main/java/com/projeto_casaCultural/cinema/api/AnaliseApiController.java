package com.projeto_casaCultural.cinema.api;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.AnaliseRepository;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analises")
@CrossOrigin(origins = "*") // permite chamadas AJAX
public class AnaliseApiController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    // GET - lista todas as análises
    @GetMapping
    public List<Analise> listarTodas() {
        return analiseRepository.findAll();
    }

    // GET - lista as análises de um filme específico
    @GetMapping("/filme/{filmeId}")
    public List<Analise> listarPorFilme(@PathVariable Long filmeId) {
        Filme filme = filmeRepository.findById(filmeId).orElse(null);
        if (filme == null) return List.of();
        return analiseRepository.findByFilme(filme);
    }

    // POST - adiciona uma nova análise
    @PostMapping
    public Analise salvar(@RequestBody Analise analise) {
        if (analise.getFilme() != null && analise.getFilme().getId() != null) {
            Filme filme = filmeRepository.findById(analise.getFilme().getId()).orElse(null);
            analise.setFilme(filme);
        }
        return analiseRepository.save(analise);
    }

    // PUT - atualiza uma análise existente
    @PutMapping("/{id}")
    public Analise atualizar(@PathVariable Long id, @RequestBody Analise analiseAtualizada) {
        return analiseRepository.findById(id).map(analise -> {
            analise.setComentario(analiseAtualizada.getComentario());
            analise.setNota(analiseAtualizada.getNota());
            return analiseRepository.save(analise);
        }).orElseThrow(() -> new RuntimeException("Análise não encontrada com id " + id));
    }

    // DELETE - remove uma análise
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        analiseRepository.deleteById(id);
    }
}
