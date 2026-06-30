package com.reviews.reviews_service.dto;

import lombok.Data;

@Data
public class ReviewsDetalleDTO {

    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private String mensaje;
    private Integer calificacion;

}
