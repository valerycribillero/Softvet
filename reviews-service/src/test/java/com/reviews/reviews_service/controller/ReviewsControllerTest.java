package com.reviews.reviews_service.controller;

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

import com.reviews.reviews_service.dto.ReviewsDetalleDTO;
import com.reviews.reviews_service.dto.ReviewsListadoDTO;
import com.reviews.reviews_service.model.Reviews;
import com.reviews.reviews_service.service.ReviewsService;

@WebMvcTest(ReviewsController.class)
public class ReviewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewsService service;

    @Test
    void listarReviews() throws Exception {
        List<Reviews> reviews = List.of(
                new Reviews(1, 1, "Excelente servicio", 5)
        );
        when(service.obtenerReview()).thenReturn(reviews);
        mockMvc.perform(get("/reviews/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarReview() throws Exception {
        String reviewJson = """
                {
                    "usuarioId": 1,
                    "mensaje": "Excelente servicio",
                    "calificacion": 5
                }
                """;
        Reviews reviewGuardada = new Reviews(1, 1, "Excelente servicio", 5);
        when(service.guardaReviews(any(Reviews.class))).thenReturn(reviewGuardada);
        mockMvc.perform(post("/reviews/agregar")
                .contentType("application/json")
                .content(reviewJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Excelente servicio"))
                .andExpect(jsonPath("$.calificacion").value(5));
    }

    @Test
    void actualizarReview() throws Exception {
        String reviewJson = """
                {
                    "usuarioId": 1,
                    "mensaje": "Servicio mejorado",
                    "calificacion": 5
                }
                """;
        when(service.actualizarReview(eq(1), any(Reviews.class)))
                .thenReturn(new Reviews(1, 1, "Servicio mejorado", 4));
        mockMvc.perform(put("/reviews/actualizar/1")
                .contentType("application/json")
                .content(reviewJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Reseña actualizada con éxito"));
    }

    @Test
    void eliminarReview() throws Exception {
        doNothing().when(service).eliminarReview(1);
        mockMvc.perform(delete("/reviews/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reseña eliminada correctamente"));
    }

    @Test
    void listarDTO() throws Exception {
        List<ReviewsListadoDTO> lista = List.of(
                new ReviewsListadoDTO()
        );
        when(service.listarDTO()).thenReturn(lista);
        mockMvc.perform(get("/reviews/listar-dto"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerDetalle() throws Exception {
        ReviewsDetalleDTO dto = new ReviewsDetalleDTO();
        dto.setMensaje("Excelente servicio");
        dto.setCalificacion(5);

        when(service.obtenerDetalle(1)).thenReturn(dto);
        mockMvc.perform(get("/reviews/1/detalle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Excelente servicio"));
    }    
}