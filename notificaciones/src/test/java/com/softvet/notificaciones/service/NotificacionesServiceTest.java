package com.softvet.notificaciones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softvet.notificaciones.model.Notificaciones;
import com.softvet.notificaciones.repository.NotificacionesRepository;

@ExtendWith(MockitoExtension.class)
class NotificacionesServiceTest {

    @Mock
    private NotificacionesRepository repository;

    @InjectMocks
    private NotificacionesService service;

    @Test
    void listarNotificaciones() {

        Notificaciones notificacion = new Notificaciones();

        notificacion.setId(1);
        notificacion.setTitulo("Vacuna pendiente");
        notificacion.setMensaje("La mascota debe vacunarse");
        notificacion.setTipo("Recordatorio");
        notificacion.setDestinatario("cliente@correo.com");
        notificacion.setFecha("2026-06-10");
        notificacion.setEstado("Pendiente");

        when(repository.findAll())
                .thenReturn(Arrays.asList(notificacion));

        List<Notificaciones> lista =
                service.listarNotificaciones();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Vacuna pendiente",
                lista.get(0).getTitulo());
        assertEquals("Pendiente",
                lista.get(0).getEstado());
    }
}
