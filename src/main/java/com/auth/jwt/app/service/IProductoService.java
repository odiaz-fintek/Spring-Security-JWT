package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Producto;


public interface IProductoService {

    /**
     * Busca un producto por su ID.
     *
     * @param id El ID del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    Producto buscarProductoPorId(Long id);

    /**
     * Guarda un producto en la base de datos.
     *
     * @param producto El producto a guardar.
     */
    void guardarProducto(Producto producto);

    // Otros métodos según tus necesidades...
}
