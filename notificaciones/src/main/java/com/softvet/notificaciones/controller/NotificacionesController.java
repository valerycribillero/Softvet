package com.softvet.notificaciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softvet.notificaciones.dto.NotificacionesListadoDTO;
import com.softvet.notificaciones.model.Notificaciones;
import com.softvet.notificaciones.service.NotificacionesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notificaciones")
@Tag(name = "Notificaciones",
     description = "API de gestión de notificaciones")
public class NotificacionesController {

    private final NotificacionesService service;

    public NotificacionesController(NotificacionesService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar una notificación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Notificación creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/agregar")
    public ResponseEntity<Notificaciones> crearNotificacion(
            @Valid @RequestBody Notificaciones notificacion) {

        Notificaciones nueva =
                service.guardarNotificacion(notificacion);

        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(summary = "Listar todas las notificaciones")
    @ApiResponse(responseCode = "200",
                 description = "Lista obtenida correctamente")
    @GetMapping("/listar")
    public List<Notificaciones> listarNotificaciones() {
        return service.listarNotificaciones();
    }

    @Operation(summary = "Buscar notificación por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificación encontrada"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> obtenerPorId(
            @PathVariable Integer id) {

        Notificaciones notificacion =
                service.obtenerPorId(id);

        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notificacion);
    }

    @Operation(summary = "Buscar notificaciones por tipo")
    @ApiResponse(responseCode = "200",
                 description = "Búsqueda realizada correctamente")
    @GetMapping("/buscar")
    public List<Notificaciones> buscarPorTipo(
            @RequestParam String tipo) {

        return service.buscarPorTipo(tipo);
    }

    @Operation(summary = "Actualizar una notificación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificación actualizada"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Notificaciones> actualizarNotificacion(
            @PathVariable Integer id,
            @Valid @RequestBody Notificaciones notificacion) {

        Notificaciones actualizada =
                service.actualizarNotificacion(id, notificacion);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una notificación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Notificación eliminada"),
        @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarNotificacion(
            @PathVariable Integer id) {

        Notificaciones notificacion =
                service.obtenerPorId(id);

        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminarNotificacion(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar notificaciones mediante DTO")
    @ApiResponse(responseCode = "200",
                 description = "DTO obtenido correctamente")
    @GetMapping("/listar-dto")
    public List<NotificacionesListadoDTO> listarDTO() {
        return service.listarDTO();
    }
}
