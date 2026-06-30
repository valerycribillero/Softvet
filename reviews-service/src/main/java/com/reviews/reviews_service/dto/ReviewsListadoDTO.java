package com.reviews.reviews_service.dto;

import lombok.Data;

@Data
public class ReviewsListadoDTO {

    private Integer usuarioId;
    private String mensaje;
    private Integer calificacion;

}
