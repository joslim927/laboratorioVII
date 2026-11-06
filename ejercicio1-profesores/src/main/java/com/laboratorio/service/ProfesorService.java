package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Profesor;
import com.laboratorio.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crearProfesor(Profesor profesor) {
        return repository.guardar(profesor);
    }

    public List<Profesor> obtenerProfesores(String especialidad) {
        if (especialidad != null && !especialidad.isBlank()) {
            return repository.buscarPorEspecialidad(especialidad);
        }
        return repository.obtenerTodos();
    }

    public Profesor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Profesor con id " + id + " no fue encontrado."));
    }
}