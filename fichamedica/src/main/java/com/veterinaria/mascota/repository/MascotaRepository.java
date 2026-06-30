package com.veterinaria.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.mascota.model.Mascota;

@Repository
public interface MascotaRepository
        extends JpaRepository<Mascota, Integer> {

}