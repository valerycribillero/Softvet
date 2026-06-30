package com.veterinaria.mascota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.service.MascotaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/mascotas")
@Tag(name = "Mascotas", description = "API de gestión de mascotas")
public class MascotaController {

    @Autowired
    private MascotaService service;

    @Operation(
        summary = "Listar mascotas",
        description = "Obtiene una lista con todas las mascotas registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<Mascota> listar() {
        return service.obtenerMascotas();
    }

    @Operation(
        summary = "Buscar mascota por ID",
        description = "Obtiene una mascota según su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/{id}")
    public Mascota obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @Operation(
        summary = "Registrar mascota",
        description = "Permite registrar una nueva mascota"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mascota registrada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public Mascota guardar(@RequestBody Mascota mascota) {
        return service.guardarMascota(mascota);
    }

    @Operation(
        summary = "Eliminar mascota",
        description = "Elimina una mascota mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminarMascota(id);
    }
}