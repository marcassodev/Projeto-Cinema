/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto_casaCultural.cinema.repositorio;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AnaliseRepository {
    private static List<Analise> analises = new ArrayList<>();
    private static Long contadorId = 1L;

    public static List<Analise> listarPorFilme(Filme filme) {
        return analises.stream()
                .filter(a -> a.getFilme().getId().equals(filme.getId()))
                .collect(Collectors.toList());
    }

    public static Analise salvar(Analise analise) {
        analise.setId(contadorId++);
        analises.add(analise);
        return analise;
    }
}
