package com.softvet.reservas.model;

import lombok.Data;

@Data
public class Pago {

    private Integer id;

    private Integer valor;

    private String metodoPago;

}
