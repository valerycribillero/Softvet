package com.softvet.reservas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ReservaDetalleDTO {

    //Datos de la reserva
    private LocalDate fecha;
    private LocalTime hora;

    //Datos del pago
    private Integer valor;
    private String metodoPago;

    //Datos del servicio
    private String nombreServicio;
    private Integer precioServicio;
}
