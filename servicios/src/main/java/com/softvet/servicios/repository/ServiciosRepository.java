package com.softvet.servicios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softvet.servicios.model.Servicios;

public interface ServiciosRepository
        extends JpaRepository<Servicios, Integer> {

    List<Servicios> findByTipoContainingIgnoreCase(String tipo);

}
