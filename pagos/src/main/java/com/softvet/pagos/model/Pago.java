package com.softvet.pagos.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pago")
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debe existir un monto total")
    @Column(nullable = false, precision = 10)
    private Integer valor;

    @NotNull(message = "Ingrese un metodo de pago")
    @Column(nullable = false, length = 30)
    private String metodoPago;


}
