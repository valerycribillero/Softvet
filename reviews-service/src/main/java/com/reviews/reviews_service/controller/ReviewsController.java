package com.reviews.reviews_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviews.reviews_service.dto.ReviewsDetalleDTO;
import com.reviews.reviews_service.dto.ReviewsListadoDTO;
import com.reviews.reviews_service.model.Reviews;
import com.reviews.reviews_service.service.ReviewsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @Operation(summary = "Listar reseñas", description = "Obtiene una lista de todas las reseñas registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar")
    public List<Reviews> listarReviews() {
        return reviewsService.obtenerReview();
    }

    @Operation(summary = "Listar reseñas DTO", description = "Obtiene una lista simplificada de reseñas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar-dto")
    public List<ReviewsListadoDTO> listarDTO() {
        return reviewsService.listarDTO();
    }

    @Operation(summary = "Obtener detalle de reseña", description = "Obtiene el detalle completo de una reseña incluyendo datos del usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle obtenido correctamente"),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/detalle")
    public ReviewsDetalleDTO obtenerDetalleDTO(@PathVariable Integer id) {
        return reviewsService.obtenerDetalle(id);
    }

    @Operation(summary = "Agregar reseña", description = "Registra una nueva reseña en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña registrada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/agregar")
    public Reviews guardarReviews(@RequestBody Reviews reviews) {
        return reviewsService.guardaReviews(reviews);
    }

    @Operation(summary = "Actualizar reseña", description = "Actualiza los datos de una reseña existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @RequestBody Reviews reviews) {
        reviewsService.actualizarReview(id, reviews);
        return "Reseña actualizada con éxito";
    }

    @Operation(summary = "Eliminar reseña", description = "Elimina una reseña por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        reviewsService.eliminarReview(id);
        return "Reseña eliminada correctamente";
    }
}