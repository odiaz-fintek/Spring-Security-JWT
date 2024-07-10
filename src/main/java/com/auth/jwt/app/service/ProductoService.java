package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Producto;
import com.auth.jwt.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    // Otros métodos...
}
