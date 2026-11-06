package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Empleado;
import com.laboratorio.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return repository.guardar(empleado);
    }

    public List<Empleado> obtenerEmpleados(String especialidad) {
        if (especialidad != null && !especialidad.isBlank()) {
            return repository.buscarPorEspecialidad(especialidad);
        }
        return repository.obtenerTodos();
    }

    public Empleado obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empleado con id " + id + " no fue encontrado."));
    }
}
