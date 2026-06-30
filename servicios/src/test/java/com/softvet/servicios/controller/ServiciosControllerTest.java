package com.softvet.servicios.controller;

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

import com.softvet.servicios.model.Servicios;
import com.softvet.servicios.service.ServiciosService;

@WebMvcTest(ServiciosController.class)
public class ServiciosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiciosService service;

    @Test
    void listarServicios() throws Exception {
        Servicios servicio = crearServicio();

        when(service.obtenerServicios()).thenReturn(List.of(servicio));

        mockMvc.perform(get("/servicios/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarServicio() throws Exception {
        String servicioJson = """
                {
                    "nombre":"Consulta General",
                    "tipo":"Consulta",
                    "precio":15000.0,
                    "descripcion":"Consulta veterinaria"
                }
                """;

        Servicios servicioGuardado = crearServicio();

        when(service.guardarServicio(any(Servicios.class))).thenReturn(servicioGuardado);

        mockMvc.perform(post("/servicios/agregar")
                .contentType("application/json")
                .content(servicioJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Consulta General"));
    }

    @Test
    void obtenerPorId() throws Exception {
        Servicios servicio = crearServicio();

        when(service.obtenerPorId(1)).thenReturn(servicio);

        mockMvc.perform(get("/servicios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarServicio() throws Exception {
        String servicioJson = """
                {
                    "nombre":"Consulta Especializada",
                    "tipo":"Consulta",
                    "precio":25000.0,
                    "descripcion":"Consulta con especialista"
                }
                """;

        Servicios actualizado = crearServicio();
        actualizado.setNombre("Consulta Especializada");
        actualizado.setPrecio(25000.0);
        actualizado.setDescripcion("Consulta con especialista");

        when(service.actualizarServicio(eq(1), any(Servicios.class))).thenReturn(actualizado);

        mockMvc.perform(put("/servicios/actualizar/1")
                .contentType("application/json")
                .content(servicioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Consulta Especializada"));
    }

    @Test
    void eliminarServicio() throws Exception {
        Servicios servicio = crearServicio();

        when(service.obtenerPorId(1)).thenReturn(servicio);
        doNothing().when(service).eliminarServicio(1);

        mockMvc.perform(delete("/servicios/eliminar/1"))
                .andExpect(status().isNoContent());
    }

    private Servicios crearServicio() {
        Servicios servicio = new Servicios();
        servicio.setId(1);
        servicio.setNombre("Consulta General");
        servicio.setTipo("Consulta");
        servicio.setPrecio(15000.0);
        servicio.setDescripcion("Consulta veterinaria");
        return servicio;
    }
}