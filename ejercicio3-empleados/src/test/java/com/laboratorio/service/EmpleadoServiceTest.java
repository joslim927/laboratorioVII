package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Empleado;
import com.laboratorio.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {

    private EmpleadoRepository repository;
    private EmpleadoService service;

    @BeforeEach
    void setUp() {
        repository = new EmpleadoRepository();
        service = new EmpleadoService(repository);
    }

    @Test
    void testCrearEmpleado() {
        Empleado empleado = new Empleado(null, "Carlos", "Gomez", "carlos@mail.com", "Sistemas", "ACTIVO");
        Empleado creado = service.crearEmpleado(empleado);

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void testObtenerEmpleadoExistente() {
        Empleado empleado = new Empleado(null, "Ana", "Lopez", "ana@mail.com", "Redes", "ACTIVO");
        Empleado guardado = service.crearEmpleado(empleado);

        Empleado encontrado = service.obtenerPorId(guardado.getId());
        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void testObtenerEmpleadoInexistente() {
        assertThrows(RecursoNoEncontradoException.class, () -> {
            service.obtenerPorId(99L);
        });
    }
}
