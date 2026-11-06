package com.laboratorio.repository;

import com.laboratorio.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Profesor guardar(Profesor profesor) {
        profesor.setId(idGenerator.getAndIncrement());
        if (profesor.getEstado() == null || profesor.getEstado().isBlank()) {
            profesor.setEstado("ACTIVO");
        }
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> obtenerTodos() {
        return new ArrayList<>(profesores);
    }

    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(p -> p.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}