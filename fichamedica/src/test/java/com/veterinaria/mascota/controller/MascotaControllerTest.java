package com.veterinaria.mascota.controller;

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

import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.service.MascotaService;

@WebMvcTest(MascotaController.class)
class MascotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MascotaService mascotaService;

    @Test
    void listarMascotas() throws Exception {
        Mascota mascota = crearMascota();

        when(mascotaService.obtenerMascotas())
                .thenReturn(List.of(mascota));

        mockMvc.perform(get("/api/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Firulais"));
    }

    @Test
    void obtenerPorId() throws Exception {
        Mascota mascota = crearMascota();

        when(mascotaService.obtenerPorId(1)).thenReturn(mascota);

        mockMvc.perform(get("/api/mascotas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Firulais"));
    }

    @Test
    void guardarMascota() throws Exception {
        String mascotaJson = """
                {
                    "nombre": "Firulais",
                    "especie": "Perro",
                    "sexo": "Macho",
                    "edad": 5
                }
                """;

        Mascota mascotaGuardada = crearMascota();

        when(mascotaService.guardarMascota(any(Mascota.class)))
                .thenReturn(mascotaGuardada);

        mockMvc.perform(post("/api/mascotas")
                .contentType("application/json")
                .content(mascotaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Firulais"));
    }

    @Test
    void eliminarMascota() throws Exception {
        doNothing().when(mascotaService).eliminarMascota(1);

        mockMvc.perform(delete("/api/mascotas/1"))
                .andExpect(status().isOk());
    }

    private Mascota crearMascota() {
        Mascota mascota = new Mascota();
        mascota.setId(1);
        mascota.setNombre("Firulais");
        mascota.setEspecie("Perro");
        mascota.setSexo("Macho");
        mascota.setEdad(5);
        return mascota;
    }
}