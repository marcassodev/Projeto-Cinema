package com.projeto_casaCultural.cinema.data;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.AnaliseRepository;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class AnaliseController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public String listarTodas(Model model) {
        List<Analise> analises = analiseRepository.findAll();
        model.addAttribute("analises", analises);
        return "analises"; // nome do HTML
    }

    @GetMapping("/novo/{filmeId}")
    public String novaAnalise(@PathVariable Long filmeId, Model model) {
        Filme filme = filmeRepository.findById(filmeId).orElse(null);
        if (filme == null) {
            return "redirect:/filmes";
        }

        Analise analise = new Analise();
        analise.setFilme(filme);
        model.addAttribute("analise", analise);
        return "form-analise";
    }

    @PostMapping("/filme/{id}")
    public String salvarAnalise(@PathVariable Long id,
            @RequestParam String comentario,
            @RequestParam Double nota) {

        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme == null) {
            return "redirect:/filmes";
        }

        Analise analise = new Analise();
        analise.setFilme(filme);
        analise.setComentario(comentario);
        analise.setNota(nota);

        analiseRepository.save(analise);

        return "redirect:/filmes/" + id;
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Analise analiseAtualizada) {
        Analise analise = analiseRepository.findById(id).orElse(null);
        if (analise != null) {
            analise.setComentario(analiseAtualizada.getComentario());
            analise.setNota(analiseAtualizada.getNota());
            analiseRepository.save(analise);
        }
        return "redirect:/filmes/" + analise.getFilme().getId();
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        Analise analise = analiseRepository.findById(id).orElse(null);
        if (analise != null) {
            Long filmeId = analise.getFilme().getId();
            analiseRepository.delete(analise);
            return "redirect:/filmes/" + filmeId;
        }
        return "redirect:/filmes";
    }
}
