/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto_casaCultural.cinema.data;


import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.AnaliseRepository;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Douglas
 */
@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filmes", FilmeRepository.listarTodos());
        return "listarFilmes";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "formFilme";
    }

    @PostMapping
    public String salvar(@ModelAttribute Filme filme) {
        FilmeRepository.salvar(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
    Filme filme = FilmeRepository.buscarPorId(id);
    if (filme == null) {
        return "redirect:/filmes";
    }
    
    List<Analise> analises = AnaliseRepository.listarPorFilme(filme);
    model.addAttribute("filme", filme);
    model.addAttribute("analises", analises);

    return "detalhesFilme";
}

}
