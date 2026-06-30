package com.reviews.reviews_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;

    @NotBlank(message = "El mensaje no debe estar vacío")
    private String mensaje;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value= 1, message = "La calificación mínima es 1")
    @Max(value = 5, message= "La calificación máxima es 5")
    private Integer calificacion;

}
