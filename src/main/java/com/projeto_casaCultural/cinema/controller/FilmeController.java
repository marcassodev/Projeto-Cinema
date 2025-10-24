package com.projeto_casaCultural.cinema.controller;

import com.projeto_casaCultural.cinema.model.Preferencia;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    // Página de listagem de filmes
    @GetMapping
    public String listarFilmes(@CookieValue(name="pref-estilo", defaultValue="claro") String tema,
                               Model model) {
        model.addAttribute("css", tema); // envia para o HTML
        return "listarFilmes";
    }

    // Grava a preferência de tema
    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        Cookie cookie = new Cookie("pref-estilo", pref.getEstilo());
        cookie.setPath("/");        // importante para evitar conflito
        cookie.setHttpOnly(false);  // para o Thymeleaf ler
        cookie.setMaxAge(86400);    // 1 dia
        response.addCookie(cookie);

        return new ModelAndView("redirect:/filmes");
    }

    @GetMapping("/novo")
    public String novoFilme() {
        return "formFilme";
    }
}

