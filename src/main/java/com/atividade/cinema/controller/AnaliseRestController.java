/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.controller;

import com.atividade.cinema.model.Analise;
import com.atividade.cinema.model.Filme;
import com.atividade.cinema.repository.AnaliseRepository;
import java.util.List;
import java.util.Optional;
import com.atividade.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    // GET: Listar todas as análises
    @GetMapping
    public List<Analise> listarAnalises() {
        // Retorna todas as ocorrências encontrads no banco de dados
        return analiseRepository.findAll();
    }
    
    // GET: Lista todas as análies de um mesmo filme
    @GetMapping("/filme/{id}")
    public ResponseEntity<List<Analise>> listarAnalisesPorFilme(@PathVariable Long id) {
        // Busca o filme por id
        Optional<Filme> filmeOptional = filmeRepository.findById(id);
           
        // Se não encontrar o filme, responde 'not found'
        if (filmeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, obtém filme e procura todas as análises daquele filme
        Filme filme = filmeOptional.get();
        List<Analise> analises = analiseRepository.findByFilme(filme);

        // Ao fim, responde 'ok' e retorna as análises
        return ResponseEntity.ok(analises);
    }


    // GET: Buscar análise por ID
    @GetMapping("/{id}")
    public ResponseEntity<Analise> buscarAnalisePorId(@PathVariable Long id) {
        // Busca analise na database
        Optional<Analise> analise = analiseRepository.findById(id);
        
        // Retorna a análise com resposta 'ok' se encontrou, ou 'not fould' caso contrário
        return analise.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST: Adicionar analise à database, junto a um filme
    @PostMapping("/filme/{filmeId}")
    public ResponseEntity<Analise> criarAnalise(@PathVariable Long filmeId, @RequestBody Analise analise) {
        // Busca o filme por id
        Optional<Filme> optionalFilme = filmeRepository.findById(filmeId);
        
        // Se não encontrar o filme, responde 'not found'
        if (optionalFilme.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, preenche o filme da análise e a adiciona à database
        analise.setFilme(optionalFilme.get());
        Analise novaAnalise = analiseRepository.save(analise);
        
        // Ao final, responde 'ok'
        return ResponseEntity.ok(novaAnalise);
    }

    // PUT: Atualizar análise existente
    @PutMapping("/{id}")
    public ResponseEntity<Analise> atualizarAnalise(@PathVariable Long id, @RequestBody Analise analiseAtualizada) {
        // Procura o analise pelo id
        Optional<Analise> optionalAnalise = analiseRepository.findById(id);
        
        // Se não encontrar a analise, responde 'not found'
        if (optionalAnalise.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, obtem a análise e executa as atualizações
        Analise analise = optionalAnalise.get();
        analise.setTexto(analiseAtualizada.getTexto());
        analise.setNota(analiseAtualizada.getNota());

        // Ao fim, salva na database e responde 'ok'
        Analise atualizada = analiseRepository.save(analise);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE: Apagar análise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Long id) {
        // Procura o analise pelo id
        Optional<Analise> optionalAnalise = analiseRepository.findById(id);
        
        // Se não encontrar a analise, responde 'not found'
        if (optionalAnalise.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, obtém a analise 
        Analise analise = optionalAnalise.get();
        
        // Ao fim, apaga a analise da database e responde 'no content'
        analiseRepository.delete(analise);
        return ResponseEntity.noContent().build();
    }
}