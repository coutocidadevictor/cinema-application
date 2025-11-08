/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.controller;

import com.atividade.cinema.model.Filme;
import com.atividade.cinema.model.Analise;
import com.atividade.cinema.service.AnaliseService;
import com.atividade.cinema.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;
    private final AnaliseService analiseService;

    public FilmeController(FilmeService filmeService, AnaliseService analiseService) {
        this.filmeService = filmeService;
        this.analiseService = analiseService;
    }

    // Página principal: lista de filmes
    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "index";
    }
    
    // Define ponto de entrada como sendo "/filmes"
    @GetMapping("/")
    public String redirecionarParaFilmes() {
        return "redirect:/filmes";
    }
    
    // Exibe formulário para cadastrar novo filme
    @GetMapping("/new")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "form";
    }
    
    // Exibe formulário para editar filme
    @GetMapping("/edit/{id}")
    public String editarFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
           
        if (filme == null) {
            System.out.println("Filme com ID " + id + " não encontrado.");
            return "redirect:/filmes";
        }
        
        model.addAttribute("filme", filme);
        model.addAttribute("modo", "editar");
        return "form";
    }

    // Recebe o envio do formulário e adiciona o filme
    @PostMapping
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeService.adicionarFilme(filme);
        return "redirect:/filmes";
    }

    // Página de detalhes de um filme
    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        
        if (filme == null){
            System.out.println("Filme com id " + id + " não encontrado.");
            return "redirect:/filmes";
        }
                
        model.addAttribute("filme", filme);
        model.addAttribute("analise", new Analise());
        return "detalhes";
    }

    // Adiciona uma análise a um filme
    @PostMapping("/{id}/analises")
    public String adicionarAnalise(@PathVariable Long id, @ModelAttribute Analise analise) {
        analiseService.adicionarAnalise(id, analise);
        return "redirect:/filmes/" + id;
    }
}
