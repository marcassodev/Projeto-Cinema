
package com.projeto_casaCultural.cinema.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/filmes")
public class FilmeController {

    // Exibe a página principal de filmes
    @GetMapping
    public String listarFilmes() {
        return "listarFilmes"; // listarFilmes.html
    }

    // Exibe o formulário para cadastrar/editar
    @GetMapping("/novo")
    public String novoFilme() {
        return "formFilme"; // formFilme.html
    }

}
