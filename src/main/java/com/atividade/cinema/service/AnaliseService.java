/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.service;

import com.atividade.cinema.model.Analise;
import com.atividade.cinema.model.Filme;
import com.atividade.cinema.repository.AnaliseRepository;
import com.atividade.cinema.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    public Analise adicionarAnalise(Long filmeId, Analise analise) {
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        analise.setId(null);
        analise.setFilme(filme);

        return analiseRepository.save(analise);
    }
    
    public List<Analise> listarAnalisesPorFilme(Long filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }
    
    public Analise buscarPorId(Long id) {
        return analiseRepository.findById(id).orElse(null);
    }
    
    public Analise atualizarAnalise(Long id, Analise analiseAtualizada) {
        Analise analiseExistente = analiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Análise não encontrada"));
        
        analiseExistente.setTexto(analiseAtualizada.getTexto());
        analiseExistente.setNota(analiseAtualizada.getNota());
        
        return analiseRepository.save(analiseExistente);
    }
    
    public void excluirAnalise(Long id) {
        if (!analiseRepository.existsById(id)) {
            throw new RuntimeException("Análise não encontrada");
        }
        analiseRepository.deleteById(id);
    }
    
    public List<Analise> listarTodasAnalises() {
        return analiseRepository.findAll();
    }
}