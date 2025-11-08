/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.controller;

import com.atividade.cinema.model.Analise;
import com.atividade.cinema.service.AnaliseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analises")
public class AnaliseController {

    private final AnaliseService analiseService;

    public AnaliseController(AnaliseService analiseService) {
        this.analiseService = analiseService;
    }

    // Exibe formulário para editar análise
    @GetMapping("/edit/{id}")
    public String editarAnalise(@PathVariable Long id, Model model) {
        Analise analise = analiseService.buscarPorId(id);

        if (analise == null) {
            System.out.println("Análise com ID " + id + " não encontrada.");
            return "redirect:/filmes"; // Redireciona para lista de filmes
        }

        model.addAttribute("analise", analise);
        model.addAttribute("modo", "editar");
        return "form-analise";
    }

    // Recebe o envio do formulário de edição de análise
    @PostMapping("/edit/{id}")
    public String atualizarAnalise(@PathVariable Long id, @ModelAttribute Analise analise) {
        analiseService.atualizarAnalise(id, analise);
        return "redirect:/filmes/" + analise.getFilme().getId();
    }
}