/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.repository;

import com.atividade.cinema.model.Analise;
import com.atividade.cinema.model.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliseRepository extends JpaRepository<Analise, Long>{
    
    List<Analise> findByFilme(Filme filme);
    List<Analise> findByFilmeId(Long filmeId);
}