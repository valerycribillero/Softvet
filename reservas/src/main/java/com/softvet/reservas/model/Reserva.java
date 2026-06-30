package com.softvet.reservas.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debe ser a un día disponible ")
    @FutureOrPresent(message= "Debe ser una fecha posible")
    private LocalDate fecha; // recordar que postman pide formato YYYY/MM/DD

    @NotNull(message = "Se debe ingresar una fecha")
    private LocalTime hora;

    @Column(nullable = false)
    private Boolean disponible = true;

    @NotNull(message = "El pago es obligatorio")
    private Integer pagoId;
    
    @NotNull(message = "El servicio es obligatorio")
    private Integer servicioId;

}
