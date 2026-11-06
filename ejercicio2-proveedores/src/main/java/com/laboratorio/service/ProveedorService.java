package com.laboratorio.service;

import com.laboratorio.exception.RecursoNoEncontradoException;
import com.laboratorio.model.Proveedor;
import com.laboratorio.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        return repository.guardar(proveedor);
    }

    public List<Proveedor> obtenerProveedores(String categoria) {
        if (categoria != null && !categoria.isBlank()) {
            return repository.buscarPorCategoria(categoria);
        }
        return repository.obtenerTodos();
    }

    public Proveedor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Proveedor con id " + id + " no fue encontrado."));
    }
}