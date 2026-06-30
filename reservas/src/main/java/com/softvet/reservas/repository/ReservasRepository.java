package com.softvet.reservas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.softvet.reservas.model.Reserva;


@Repository
public interface ReservasRepository extends JpaRepository<Reserva, Integer>{
    Optional<Reserva> findById(Integer id);
}
