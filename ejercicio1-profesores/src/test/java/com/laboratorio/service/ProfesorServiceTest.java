package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Profesor;
import com.laboratorio.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorServiceTest {

    private ProfesorRepository repository;
    private ProfesorService service;

    @BeforeEach
    void setUp() {
        repository = new ProfesorRepository();
        service = new ProfesorService(repository);
    }

    @Test
    void testCrearProfesor() {
        Profesor profesor = new Profesor(null, "Carlos", "Gomez", "carlos@mail.com", "Matematicas", "ACTIVO");
        Profesor creado = service.crearProfesor(profesor);

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void testObtenerProfesorExistente() {
        Profesor profesor = new Profesor(null, "Ana", "Lopez", "ana@mail.com", "Fisica", "ACTIVO");
        Profesor guardado = service.crearProfesor(profesor);

        Profesor encontrado = service.obtenerPorId(guardado.getId());
        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void testObtenerProfesorInexistente() {
        assertThrows(RecursoNoEncontradoException.class, () -> {
            service.obtenerPorId(99L);
        });
    }
}