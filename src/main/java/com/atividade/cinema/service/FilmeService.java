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
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/*
Esta classe não está sendo utilizada no momento, mas foi útil para fazer a
transição do armazenamento em memória para armazenamento em banco de dados
*/

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;
        
    public Filme adicionarFilme(Filme filme){
        filme.setId(null);
        return filmeRepository.save(filme);
    }
    
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }
    
    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id).orElse(null);
    }
}
