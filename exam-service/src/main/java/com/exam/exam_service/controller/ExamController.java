package com.exam.exam_service.controller;

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

import com.exam.exam_service.dto.ExamDetalleDTO;
import com.exam.exam_service.dto.ExamListadoDTO;
import com.exam.exam_service.model.Exam;
import com.exam.exam_service.service.ExamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/examenes")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Operation(summary = "Listar exámenes", description = "Obtiene una lista de todos los exámenes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron registros"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar")
    public List<Exam> listarExamenes() {
        return examService.obtenerExamenes();
    }

    @Operation(summary = "Agregar examen", description = "Registra un nuevo examen en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Examen registrado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/agregar")
    public Exam agregarExamen(@Valid @RequestBody Exam exam) {
        return examService.agregarExamen(exam);
    }

    @Operation(summary = "Obtener examen por ID", description = "Busca un examen por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Examen encontrado"),
        @ApiResponse(responseCode = "404", description = "Examen no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/obtener/{id}")
    public Exam obtenerExam(@PathVariable Integer id) {
        return examService.obtenerPorId(id);
    }

    @Operation(summary = "Eliminar examen", description = "Elimina un examen por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Examen eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Examen no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        examService.eliminarExamen(id);
        return "Examen eliminado correctamente";
    }

    @Operation(summary = "Actualizar examen", description = "Actualiza los datos de un examen existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Examen actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Examen no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @RequestBody Exam exam) {
        examService.actualizarExamen(id, exam);
        return "Examen actualizado con éxito";
    }

    @Operation(summary = "Listar exámenes DTO", description = "Obtiene una lista simplificada de exámenes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar-dto")
    public List<ExamListadoDTO> listarDTO() {
        return examService.listarDTO();
    }

    @Operation(summary = "Obtener detalle de examen", description = "Obtiene el detalle completo de un examen incluyendo datos de la mascota")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle obtenido correctamente"),
        @ApiResponse(responseCode = "404", description = "Examen no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/detalle")
    public ExamDetalleDTO obtenerDetalle(@PathVariable Integer id) {
        return examService.obtenerDetalle(id);
    }
}