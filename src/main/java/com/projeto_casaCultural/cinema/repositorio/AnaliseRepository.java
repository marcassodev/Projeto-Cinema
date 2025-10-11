/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto_casaCultural.cinema.repositorio;

import com.projeto_casaCultural.cinema.model.Analise;
import com.projeto_casaCultural.cinema.model.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long> { 
        List<Analise> findByFilme(Filme filme);
}
