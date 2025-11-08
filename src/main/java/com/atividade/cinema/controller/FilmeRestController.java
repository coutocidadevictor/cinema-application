/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.controller;

import com.atividade.cinema.model.Filme;
import com.atividade.cinema.repository.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*") 
public class FilmeRestController {

    @Autowired
    private FilmeRepository filmeRepository;

    // GET: Listar todos os filmes
    @GetMapping
    public List<Filme> listarFilmes() {
        // Retorna todas as ocorrências encontrads no banco de dados
        return filmeRepository.findAll();
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        // Busca filme na DataBase
        Optional<Filme> filme = filmeRepository.findById(id);
        
        // Retorna o filme com resposta 'ok' se encontrou, ou 'not fould' caso contrário
        return filme.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    // POST: Adicionar filme à database
    @PostMapping
    public Filme salvarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }   
    
    // PUT: Atualizar filme
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        // Procura o filme pelo id
        Optional<Filme> optionalFilme = filmeRepository.findById(id);
        
        // Se não encontrar o filme, responde 'not found'
        if (optionalFilme.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, obtem o filme e executa todas as atualizações
        Filme filme = optionalFilme.get();
        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setSinopse(filmeAtualizado.getSinopse());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());

        // Ao fim, salva na database e responde 'ok'
        Filme atualizado = filmeRepository.save(filme);
        return ResponseEntity.ok(atualizado);
    }
    
    // DELETE: Apagar filme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        // Procura o filme pelo id
        Optional<Filme> optionalFilme = filmeRepository.findById(id);
        
        // Se não encontrar o filme, responde 'not found'
        if (optionalFilme.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Caso contrário, obtém o filme 
        Filme filme = optionalFilme.get();
        
        // Ao fim, apaga o filme da database e responde 'no content'
        filmeRepository.delete(filme);
        return ResponseEntity.noContent().build();
    }

}
