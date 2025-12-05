package com.udea.ParcialPractico.repository;

import com.udea.ParcialPractico.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Optional<Materia> findByNombre(String nombre);
}
