package com.exam.exam_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExamListadoDTO {
    private String resultado;

    private Integer mascotaId;

    private LocalDate fecha;
}
