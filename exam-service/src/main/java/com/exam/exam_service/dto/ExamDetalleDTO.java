package com.exam.exam_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamDetalleDTO {
    
    private String nombreMascota;
    private String raza;
    private String edad;
    private String resultado;
    private LocalDate fecha;
}
