package com.laboratorio.repository;

import com.laboratorio.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProveedorRepository {

    private final List<Proveedor> proveedores = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(idGenerator.getAndIncrement());
        if (proveedor.getEstado() == null || proveedor.getEstado().isBlank()) {
            proveedor.setEstado("ACTIVO");
        }
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> obtenerTodos() {
        return new ArrayList<>(proveedores);
    }

    public List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
