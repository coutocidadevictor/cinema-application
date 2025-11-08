/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.atividade.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    private String texto;
    private double nota;
    
    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonBackReference
    private Filme filme;

    public Analise() {}

    public Analise(Long id, String texto, double nota) {
        this.id = id;
        this.texto = texto;
        this.nota = nota;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
    
    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    
}
