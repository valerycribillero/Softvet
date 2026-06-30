package com.softvet.autenticacion.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.softvet.autenticacion.model.Usuario;
import com.softvet.autenticacion.service.AutenService;

@WebMvcTest(controllers = AutenController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class AutenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutenService service;

    @Test
    void listar() throws Exception {

        when(service.listar()).thenReturn(List.of(crearUsuario()));

        mockMvc.perform(get("/registro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("karla"));
    }

    @Test
    void guardarUsuario() throws Exception {

        String userJson = """
                {
                  "nombre":"karla",
                  "apellido":"ferrada",
                  "email":"kfc@gmail.com",
                  "password":"1234"
                }
                """;

        when(service.guardarUsuario(any(Usuario.class)))
                .thenReturn(crearUsuario());

        mockMvc.perform(post("/registro/agregar")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("karla"))
                .andExpect(jsonPath("$.apellido").value("ferrada"));
    }

    @Test
    void buscarPorId() throws Exception {

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(crearUsuario()));

        mockMvc.perform(get("/registro/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("karla"));
    }

    @Test
    void actualizarUsuario() throws Exception {

        String userJson = """
                {
                  "nombre":"mateo",
                  "apellido":"matraca",
                  "email":"mateo@gmail.com",
                  "password":"5678"
                }
                """;

        Usuario actualizado = new Usuario();
        actualizado.setId(1);
        actualizado.setNombre("mateo");
        actualizado.setApellido("matraca");
        actualizado.setEmail("mateo@gmail.com");
        actualizado.setPassword("5678");

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(crearUsuario()));

        when(service.actualizarUsuario(eq(1), any(Usuario.class)))
                .thenReturn(actualizado);

        mockMvc.perform(put("/registro/actualizar/1")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario actualizado correctamente"));
    }

    @Test
    void eliminarUsuario() throws Exception {

        when(service.buscarPorId(1))
                .thenReturn(Optional.of(crearUsuario()));

        doNothing().when(service).eliminarPorId(1);

        mockMvc.perform(delete("/registro/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario fue eliminado correctamente"));
    }

    private Usuario crearUsuario() {

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("karla");
        usuario.setApellido("ferrada");
        usuario.setEmail("kfc@gmail.com");
        usuario.setPassword("1234");

        return usuario;
    }
}