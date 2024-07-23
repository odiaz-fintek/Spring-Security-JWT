package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Producto;
import com.auth.jwt.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class represents a service for managing products.
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    @Override
    @Transactional(readOnly = true)
    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Saves a product.
     *
     * @param producto The product to save.
     */
    @Override
    @Transactional(readOnly = false)
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    // Other methods...
}
