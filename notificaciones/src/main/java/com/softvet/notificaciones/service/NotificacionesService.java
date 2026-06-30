package com.softvet.notificaciones.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softvet.notificaciones.dto.NotificacionesListadoDTO;
import com.softvet.notificaciones.model.Notificaciones;
import com.softvet.notificaciones.repository.NotificacionesRepository;

@Service
public class NotificacionesService {

    private final NotificacionesRepository repository;

    public NotificacionesService(NotificacionesRepository repository) {
        this.repository = repository;
    }

    public List<Notificaciones> listarNotificaciones() {
        return repository.findAll();
    }

    public Notificaciones guardarNotificacion(
            Notificaciones notificacion) {

        return repository.save(notificacion);
    }

    public Notificaciones obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminarNotificacion(Integer id) {
        repository.deleteById(id);
    }

    public Notificaciones actualizarNotificacion(
            Integer id,
            Notificaciones notificacion) {

        Notificaciones existente =
                repository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setTitulo(notificacion.getTitulo());
        existente.setMensaje(notificacion.getMensaje());
        existente.setTipo(notificacion.getTipo());
        existente.setDestinatario(notificacion.getDestinatario());
        existente.setFecha(notificacion.getFecha());
        existente.setEstado(notificacion.getEstado());

        return repository.save(existente);
    }

    public List<Notificaciones> buscarPorTipo(String tipo) {
        return repository.findByTipoContainingIgnoreCase(tipo);
    }

    public List<NotificacionesListadoDTO> listarDTO() {

        List<Notificaciones> notificaciones =
                repository.findAll();

        List<NotificacionesListadoDTO> lista =
                new ArrayList<>();

        for (Notificaciones n : notificaciones) {

            NotificacionesListadoDTO dto =
                    new NotificacionesListadoDTO();

            dto.setId(n.getId());
            dto.setTitulo(n.getTitulo());
            dto.setTipo(n.getTipo());
            dto.setEstado(n.getEstado());

            lista.add(dto);
        }

        return lista;
    }
}