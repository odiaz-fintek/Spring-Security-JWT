package com.auth.jwt.app.controller.ApikeyControllers;

import com.auth.jwt.app.entity.Producto;
import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.repository.ProductoRepository;
import com.auth.jwt.app.service.IProductoService;
import com.auth.jwt.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apikey/productos")
public class ProductoController_apikey {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        // Buscar el producto existente
        Producto productoExistente = productoService.buscarProductoPorId(id);
        if (productoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar las propiedades del producto
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setDescripcion(productoDetalles.getDescripcion());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setCantidad(productoDetalles.getCantidad());
        productoExistente.setFechaCreacion(productoDetalles.getFechaCreacion());
        productoExistente.setFechaActualizacion(productoDetalles.getFechaActualizacion());

        // Asignar el ID del usuario
        Integer usuarioId = productoDetalles.getUsuarioId();
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if (usuario != null) {
            productoExistente.setUsuarioId(usuarioId);
        } else {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        // Guardar el producto actualizado
        productoService.guardarProducto(productoExistente);

        return ResponseEntity.ok(productoExistente);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}