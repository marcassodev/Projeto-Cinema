/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto_casaCultural.cinema.repositorio;

import com.projeto_casaCultural.cinema.model.Filme;
import java.util.ArrayList;
import java.util.List;

public class FilmeRepository {
    private static List<Filme> filmes = new ArrayList<>();
    private static long contadorId = 1L;

    public static List<Filme> listarTodos() {
        return filmes;
    }

    public static void salvar(Filme filme) {
    filme.setId(contadorId++);    
    filmes.add(filme);
    }

    public static Filme buscarPorId(Long id) {
        return filmes.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    } 
}
