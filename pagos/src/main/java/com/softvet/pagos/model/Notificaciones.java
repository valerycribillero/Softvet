package com.softvet.pagos.model;

import lombok.Data;

@Data
public class Notificaciones {
    private String titulo;
    private String mensaje;
    private String tipo;
    private String destinatario;
    private String fecha;
    private String estado;
}
