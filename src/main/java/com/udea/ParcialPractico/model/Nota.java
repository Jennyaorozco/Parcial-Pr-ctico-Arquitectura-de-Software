package com.udea.ParcialPractico.model;

import jakarta.persistence.*;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Estudiante estudiante;

    @ManyToOne(optional = false)
    private Materia materia;

    private Double valor;

    public Nota() {
    }

    public Nota(Long id, Estudiante estudiante, Materia materia, Double valor) {
        this.id = id;
        this.estudiante = estudiante;
        this.materia = materia;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Materia getMateria() {
        return materia;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
