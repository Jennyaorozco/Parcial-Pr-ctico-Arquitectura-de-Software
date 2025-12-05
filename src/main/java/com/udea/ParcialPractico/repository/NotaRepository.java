package com.udea.ParcialPractico.repository;

import com.udea.ParcialPractico.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByEstudianteCedula(String cedula);
}
