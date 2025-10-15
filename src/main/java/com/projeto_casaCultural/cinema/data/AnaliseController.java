package com.projeto_casaCultural.cinema.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class AnaliseController {
    
    // Exibe a tela para cadastrar/editar analise
   @GetMapping
    public String listarAnalises() {
        return "listarAnalises"; // listarAnalises.html
    }
    
    // Exibe o formulário para nova analise
    @GetMapping("/nova")
    public String novaAnalise() {
        return "formAnalise"; // formAnalise.html
    }
}
