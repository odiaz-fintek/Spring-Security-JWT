package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
