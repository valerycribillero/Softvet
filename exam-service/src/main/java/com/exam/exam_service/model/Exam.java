package com.exam.exam_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="examenes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El resultado no puede estar vacío")
    private String resultado;

    @NotNull(message = "El paciente es obligatorio")
    private Integer mascotaId;

    //"2026-06-02"
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
}
