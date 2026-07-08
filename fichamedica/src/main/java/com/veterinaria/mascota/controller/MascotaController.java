package com.veterinaria.mascota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Mascotas", description = "API para gestión de mascotas")
public class MascotaController {

    @Autowired
    private MascotaService service;


    @Operation(
        summary = "Listar mascotas",
        description = "Obtiene todas las mascotas registradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(service.obtenerMascotas());
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
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }


    @Operation(
        summary = "Registrar mascota",
        description = "Permite registrar una nueva mascota"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mascota registrada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/agregar")
    public ResponseEntity<Mascota> guardar(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = service.guardarMascota(mascota);
        return ResponseEntity.status(201).body(nuevaMascota);
    }


    @Operation(
        summary = "Eliminar mascota",
        description = "Elimina una mascota mediante su identificador"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        service.eliminarMascota(id);

        return ResponseEntity.ok("Mascota eliminada correctamente");
    }
}