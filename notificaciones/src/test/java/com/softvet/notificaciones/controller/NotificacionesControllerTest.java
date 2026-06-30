package com.softvet.notificaciones.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.softvet.notificaciones.model.Notificaciones;
import com.softvet.notificaciones.service.NotificacionesService;

@WebMvcTest(NotificacionesController.class)
class NotificacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionesService service;

    @Test
    void listarNotificaciones() throws Exception {
        List<Notificaciones> notificaciones = List.of(
            crearNotificacion()
        );

        when(service.listarNotificaciones()).thenReturn(notificaciones);

        mockMvc.perform(get("/notificaciones/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarNotificacion() throws Exception {
        String notificacionJson = """
                {
                    "titulo": "Vacuna pendiente",
                    "mensaje": "La mascota debe vacunarse",
                    "tipo": "Recordatorio",
                    "destinatario": "cliente@correo.com",
                    "fecha": "2026-06-10",
                    "estado": "Pendiente"
                }
                """;

        Notificaciones notificacionGuardada = crearNotificacion();

        when(service.guardarNotificacion(any(Notificaciones.class)))
                .thenReturn(notificacionGuardada);

        mockMvc.perform(post("/notificaciones/agregar")
                .contentType("application/json")
                .content(notificacionJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Vacuna pendiente"));
    }

    @Test
    void obtenerPorId() throws Exception {
        Notificaciones notificacion = crearNotificacion();

        when(service.obtenerPorId(1)).thenReturn(notificacion);

        mockMvc.perform(get("/notificaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorTipo() throws Exception {
        List<Notificaciones> notificaciones = List.of(
            crearNotificacion()
        );

        when(service.buscarPorTipo("Recordatorio"))
                .thenReturn(notificaciones);

        mockMvc.perform(get("/notificaciones/buscar")
                .param("tipo", "Recordatorio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("Recordatorio"));
    }

    @Test
    void actualizarNotificacion() throws Exception {
        String notificacionJson = """
                {
                    "titulo": "Vacuna actualizada",
                    "mensaje": "La mascota debe vacunarse pronto",
                    "tipo": "Recordatorio",
                    "destinatario": "cliente@correo.com",
                    "fecha": "2026-06-11",
                    "estado": "Pendiente"
                }
                """;

        Notificaciones notificacionActualizada = crearNotificacion();
        notificacionActualizada.setTitulo("Vacuna actualizada");
        notificacionActualizada.setMensaje("La mascota debe vacunarse pronto");
        notificacionActualizada.setFecha("2026-06-11");

        when(service.actualizarNotificacion(eq(1), any(Notificaciones.class)))
                .thenReturn(notificacionActualizada);

        mockMvc.perform(put("/notificaciones/actualizar/1")
                .contentType("application/json")
                .content(notificacionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Vacuna actualizada"));
    }

    @Test
    void eliminarNotificacion() throws Exception {
        Notificaciones notificacion = crearNotificacion();

        when(service.obtenerPorId(1)).thenReturn(notificacion);
        doNothing().when(service).eliminarNotificacion(1);

        mockMvc.perform(delete("/notificaciones/eliminar/1"))
                .andExpect(status().isNoContent());
    }

    private Notificaciones crearNotificacion() {
        Notificaciones notificacion = new Notificaciones();

        notificacion.setId(1);
        notificacion.setTitulo("Vacuna pendiente");
        notificacion.setMensaje("La mascota debe vacunarse");
        notificacion.setTipo("Recordatorio");
        notificacion.setDestinatario("cliente@correo.com");
        notificacion.setFecha("2026-06-10");
        notificacion.setEstado("Pendiente");

        return notificacion;
    }
}