package com.laboratorio.controller;

import com.laboratorio.model.Profesor;
import com.laboratorio.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@Valid @RequestBody Profesor profesor) {
        Profesor nuevo = service.crearProfesor(profesor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> obtenerTodos(@RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.obtenerProfesores(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}