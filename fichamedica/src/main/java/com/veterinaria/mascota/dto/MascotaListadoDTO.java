package com.veterinaria.mascota.dto;

import lombok.Data;

@Data
public class MascotaListadoDTO {

    private String nombre;
    private String especie;
    private String sexo;
    private Integer edad;
}