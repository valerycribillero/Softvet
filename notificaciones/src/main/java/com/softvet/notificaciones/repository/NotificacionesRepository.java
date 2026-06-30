package com.softvet.notificaciones.repository;

import com.softvet.notificaciones.model.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionesRepository
        extends JpaRepository<Notificaciones, Integer> {

    List<Notificaciones> findByTipoContainingIgnoreCase(String tipo);

}
