package com.softvet.servicios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiciosListadoDTO {

    private Integer id;
    private String nombre;
    private String tipo;
    private Double precio;
    private String descripcion;

}
