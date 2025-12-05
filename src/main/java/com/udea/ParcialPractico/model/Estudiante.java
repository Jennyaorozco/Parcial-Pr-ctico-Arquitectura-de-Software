package com.udea.ParcialPractico.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cedula;

    private String nombre;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Nota> notas;

    public Estudiante() {
    }

    public Estudiante(Long id, String cedula, String nombre, List<Nota> notas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.notas = notas;
    }

    public Long getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}

