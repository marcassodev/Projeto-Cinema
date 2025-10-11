
package com.projeto_casaCultural.cinema.data;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.service.AnaliseService;
import com.projeto_casaCultural.cinema.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private AnaliseService analiseService;

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        return "listarFilmes";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "formFilme";
    }

    @PostMapping
    public String salvar(@ModelAttribute Filme filme) {
        filmeService.salvar(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme == null) {
            return "redirect:/filmes";
        }

        List<Analise> analises = analiseService.listarPorFilme(id);
        model.addAttribute("filme", filme);
        model.addAttribute("analises", analises);

        return "detalhesFilme";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return "redirect:/filmes";
    }

}
