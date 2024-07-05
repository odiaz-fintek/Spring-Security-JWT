package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByfirstname(String firstname);
    List<Cliente> findBystatus(String  status);
    List<Cliente> findBylocationCode(String locationCode);
    List<Cliente> findBycreateDate(Date creatDate);
}
