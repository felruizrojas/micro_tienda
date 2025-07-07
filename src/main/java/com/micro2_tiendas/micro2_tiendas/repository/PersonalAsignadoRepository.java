package com.micro2_tiendas.micro2_tiendas.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro2_tiendas.micro2_tiendas.model.PersonalAsignado;

@Repository

public interface PersonalAsignadoRepository extends JpaRepository<PersonalAsignado, Integer>{

    Collection<PersonalAsignado> findByTiendaIdTienda(int idTienda);
    
}
