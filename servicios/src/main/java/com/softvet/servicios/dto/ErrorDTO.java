package com.softvet.servicios.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private LocalDateTime fecha;
    private Integer codigo;
    private String mensaje;
    private Map<String, String> errores;
    private String ruta;

    public ErrorDTO(
            LocalDateTime fecha,
            Integer codigo,
            String mensaje,
            Map<String, String> errores,
            String ruta) {

        this.fecha = fecha;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.errores = errores;
        this.ruta = ruta;
    }
}
