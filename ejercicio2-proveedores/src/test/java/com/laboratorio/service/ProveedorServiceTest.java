package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Proveedor;
import com.laboratorio.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorServiceTest {

    private ProveedorRepository repository;
    private ProveedorService service;

    @BeforeEach
    void setUp() {
        repository = new ProveedorRepository();
        service = new ProveedorService(repository);
    }

    @Test
    void testCrearProveedor() {
        Proveedor proveedor = new Proveedor(null, "Carlos", "Gomez", "carlos@mail.com", "Papeleria", "ACTIVO");
        Proveedor creado = service.crearProveedor(proveedor);

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void testObtenerProveedorExistente() {
        Proveedor proveedor = new Proveedor(null, "Ana", "Lopez", "ana@mail.com", "Tecnologia", "ACTIVO");
        Proveedor guardado = service.crearProveedor(proveedor);

        Proveedor encontrado = service.obtenerPorId(guardado.getId());
        assertNotNull(encontrado);
        assertEquals("Ana", encontrado.getNombre());
    }

    @Test
    void testObtenerProveedorInexistente() {
        assertThrows(RecursoNoEncontradoException.class, () -> {
            service.obtenerPorId(99L);
        });
    }
}