package com.udea.ParcialPractico.controller;

import com.udea.ParcialPractico.model.Nota;
import com.udea.ParcialPractico.service.EstudianteService;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }


    @GetMapping("/{cedula}/notas")
    public CollectionModel<EntityModel<NotaResponse>> getNotas(@PathVariable String cedula) {

        List<Nota> notas = service.getNotasByCedula(cedula);

        List<EntityModel<NotaResponse>> recursos = new ArrayList<>();

        for (Nota n : notas) {

            NotaResponse r = new NotaResponse(
                    n.getMateria().getNombre(),
                    n.getValor()
            );

            EntityModel<NotaResponse> em = EntityModel.of(r);

            em.add(linkTo(methodOn(EstudianteController.class)
                    .getNotas(cedula)).withSelfRel());

            recursos.add(em);
        }

        Link self = linkTo(methodOn(EstudianteController.class)
                .getNotas(cedula)).withSelfRel();

        return CollectionModel.of(recursos, self);
    }


    @PostMapping("/{cedula}/notas")
    public ResponseEntity<EntityModel<Map<String, Object>>> addNotas(
            @PathVariable String cedula,
            @RequestBody List<EstudianteService.NotaInput> notas) {

        service.addNotas(cedula, notas);

        Link notasLink = linkTo(methodOn(EstudianteController.class)
                .getNotas(cedula)).withRel("ver-notas");

        Map<String, Object> body = Map.of(
                "message", "Notas agregadas correctamente",
                "_links", Map.of("ver-notas", notasLink.getHref())
        );

        EntityModel<Map<String, Object>> resource =
                EntityModel.of(body, notasLink);

        return ResponseEntity
                .created(notasLink.toUri())
                .body(resource);
    }

    public record NotaResponse(String materia, Double valor) {}
}
