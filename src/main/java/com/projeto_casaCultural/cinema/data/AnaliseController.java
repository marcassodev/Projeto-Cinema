
package com.projeto_casaCultural.cinema.data;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import com.projeto_casaCultural.cinema.repositorio.AnaliseRepository;
import com.projeto_casaCultural.cinema.repositorio.FilmeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class AnaliseController {

    @PostMapping("/filme/{id}")
    public String salvarAnalise(@PathVariable Long id,
                            @RequestParam String comentario,
                            @RequestParam int nota) {
    Filme filme = FilmeRepository.buscarPorId(id);
    if (filme == null) {
        return "redirect:/filmes";
    }

    Analise analise = new Analise();
    analise.setFilme(filme);
    analise.setComentario(comentario);
    analise.setNota(nota);

    AnaliseRepository.salvar(analise);

    return "redirect:/filmes/" + id;
    }
}