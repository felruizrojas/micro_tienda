package com.micro2_tiendas.micro2_tiendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro2_tiendas.micro2_tiendas.model.Tienda;

@Repository

public interface TiendaRepository extends JpaRepository<Tienda, Integer>{

    List<Tienda> findByActivaTrue();
    
}
