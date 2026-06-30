package com.softvet.reservas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softvet.reservas.dto.ReservaDetalleDTO;
import com.softvet.reservas.model.Reserva;
import com.softvet.reservas.service.ReservasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService service;

    @Operation(
        summary = "Listar",
        description = "Obtiene una lista de todas las reservas registradas"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @GetMapping
    public List<Reserva> listar(){
        return service.listar();
    }
    @Operation(
        summary = "Listar por ID",
        description = "Obtiene una lista de todas las reservas registradas con su ID"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @GetMapping("/{id}")
    public Optional<Reserva> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @Operation(
        summary = "Agregar una nueva reserva",
        description = "Registra agrega una nueva reserva"    
)  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva agregada correctamente"),
            @ApiResponse(responseCode= "201", description = "Se guardaron los datos correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @PostMapping("/agregar")
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva){
        Reserva nueva = service.guardarReserva(reserva);
        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(
        summary = "Eliminar una reserva",
        description = "Elimina una reserva ya creada"    
)  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva eliminada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Reserva> reserva = service.buscarPorId(id);

        if (reserva.isPresent()){
            service.eliminarPorId(id);
            return "Cita eliminada exitosamente";
        }else{
            return "Cita no encontrada con id:"+id;
        }
    }

    @Operation(
        summary = "Actualizar una reserva",
        description = "Actualiza una reserva ya creada"    
)  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva agregada correctamente correctamente"),
            @ApiResponse(responseCode= "201", description = "Se guardaron los datos correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})

    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Reserva reserva){
        Optional<Reserva> existente = service.buscarPorId(id);

        if (existente.isPresent()){
            service.actualizarReserva(id, reserva);
            return "Cita actualizada correctamente";
        }else{
            return "Cita no encontrada con id:"+id;
        }
    }

    @Operation(
        summary = "Obtener boleta completa",
        description = "Te da la boleta completa"    
)  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})

    @GetMapping("/{id}/boleta")
    public ReservaDetalleDTO obtenerBoleta(@PathVariable Integer id){
        return service.obtenerDetalle(id);
    }   
}
