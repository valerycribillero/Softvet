package com.exam.exam_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.exam.exam_service.dto.ExamDetalleDTO;
import com.exam.exam_service.model.Exam;
import com.exam.exam_service.service.ExamService;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Test
    void listarExamenes() throws Exception {
        List<Exam> exams = List.of(
                new Exam(1, "Bien", 1, LocalDate.parse("2026-06-02"))
        );
        when(examService.obtenerExamenes()).thenReturn(exams);
        mockMvc.perform(get("/examenes/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarExamen() throws Exception {
        String examJson = """
                {
                    "resultado": "Bien",
                    "mascotaId": 1,
                    "fecha": "2026-06-02"
                }
                """;
        Exam examGuardado = new Exam(1, "Bien", 1, LocalDate.parse("2026-06-02"));
        when(examService.agregarExamen(any(Exam.class))).thenReturn(examGuardado);
        mockMvc.perform(post("/examenes/agregar")
                .contentType("application/json")
                .content(examJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value("Bien"))
                .andExpect(jsonPath("$.mascotaId").value(1));
    }

    @Test
    void obtenerPorId() throws Exception {
        Exam exam = new Exam(1, "Bien", 1, LocalDate.parse("2026-06-02"));
        when(examService.obtenerPorId(1)).thenReturn(exam);
        mockMvc.perform(get("/examenes/obtener/1"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarExamen() throws Exception {
        String examJson = """
                {
                    "resultado": "Mejorado",
                    "mascotaId": 1,
                    "fecha": "2026-06-02"
                }
                """;
        when(examService.actualizarExamen(eq(1), any(Exam.class)))
                .thenReturn(new Exam(1, "Mejorado", 1, LocalDate.parse("2026-06-02")));
        mockMvc.perform(put("/examenes/actualizar/1")
                .contentType("application/json")
                .content(examJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Examen actualizado con éxito"));
    }

    @Test
    void eliminarExamen() throws Exception {
        doNothing().when(examService).eliminarExamen(1);
        mockMvc.perform(delete("/examenes/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Examen eliminado correctamente"));
    }
    @Test
    void obtenerDetalle() throws Exception {
        ExamDetalleDTO dto = new ExamDetalleDTO();
        dto.setResultado("Bien");
        dto.setFecha(LocalDate.parse("2026-06-02"));
        
        when(examService.obtenerDetalle(1)).thenReturn(dto);
        mockMvc.perform(get("/examenes/1/detalle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado").value("Bien"));
    }

}