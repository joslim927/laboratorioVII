package com.laboratorio.repository;

import com.laboratorio.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class EmpleadoRepository {

    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Empleado guardar(Empleado empleado) {
        empleado.setId(idGenerator.getAndIncrement());
        if (empleado.getEstado() == null || empleado.getEstado().isBlank()) {
            empleado.setEstado("ACTIVO");
        }
        empleados.add(empleado);
        return empleado;
    }

    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(e -> e.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}