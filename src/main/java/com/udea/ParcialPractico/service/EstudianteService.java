package com.udea.ParcialPractico.service;

import com.udea.ParcialPractico.model.Estudiante;
import com.udea.ParcialPractico.model.Materia;
import com.udea.ParcialPractico.model.Nota;
import com.udea.ParcialPractico.repository.EstudianteRepository;
import com.udea.ParcialPractico.repository.MateriaRepository;
import com.udea.ParcialPractico.repository.NotaRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepo;
    private final MateriaRepository materiaRepo;
    private final NotaRepository notaRepo;

    public EstudianteService(EstudianteRepository e, MateriaRepository m, NotaRepository n) {
        this.estudianteRepo = e;
        this.materiaRepo = m;
        this.notaRepo = n;
    }

    public Estudiante getByCedula(String cedula) {
        return estudianteRepo.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("Estudiante no encontrado"));
    }

    public List<Nota> getNotasByCedula(String cedula) {
        return notaRepo.findByEstudianteCedula(cedula);
    }

    public void addNotas(String cedula, List<NotaInput> notas) {

        Estudiante est = estudianteRepo.findByCedula(cedula)
                .orElseGet(() -> {
                    Estudiante nuevo = new Estudiante();
                    nuevo.setCedula(cedula);
                    nuevo.setNombre("SinNombre");
                    return estudianteRepo.save(nuevo);
                });

        for (NotaInput ni : notas) {

            Materia mat = materiaRepo.findByNombre(ni.materia())
                    .orElseGet(() -> {
                        Materia nueva = new Materia();
                        nueva.setNombre(ni.materia());
                        return materiaRepo.save(nueva);
                    });

            Nota nota = new Nota();
            nota.setEstudiante(est);
            nota.setMateria(mat);
            nota.setValor(ni.valor());

            notaRepo.save(nota);
        }
    }

    public record NotaInput(String materia, Double valor) {}
}
