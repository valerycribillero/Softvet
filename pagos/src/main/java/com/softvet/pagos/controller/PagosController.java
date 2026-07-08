package com.softvet.pagos.controller;

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

import com.softvet.pagos.model.Pago;
import com.softvet.pagos.service.PagosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RequestMapping("/pagos")
@RestController
public class PagosController {

    @Autowired
    private PagosService service;

    @Operation(
    summary = "Listar boleta",
    description = "Obtiene una lista con todos los pagos"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @GetMapping
    public List<Pago> listar(){
        return service.listar();
    }

    @Operation(
    summary = "Buscar boleta",
    description = "Busca pagos por id"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
     @GetMapping("/{id}")
    public Optional<Pago> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @Operation(
    summary = "Agregar nuevo pago",
    description = "Agrega un nuevo pago realizado"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago agregado correctamente"),
        @ApiResponse(responseCode = "201", description = "Datos guardados correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @PostMapping("/agregar")
    public ResponseEntity<Pago> crearPago(@Valid @RequestBody Pago pago){
        Pago nueva = service.guardarPago(pago);
        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(
    summary = "Eliminar pago",
    description = "Elimina un pago ya realizado"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        Optional<Pago> pago = service.buscarPorId(id);

        if (pago.isPresent()){
            service.eliminarPorId(id);
            return "Pago eliminado exitosamente";
        }else{
            return "Pago no encontrado con id:"+id;
        }
    }

    @Operation(
    summary = "Actualizar pago",
    description = "Actualiza los datos de un pago realizado anteriormente"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Pago pago){
        Optional<Pago> existente = service.buscarPorId(id);

        if (existente.isPresent()){
            service.actualizarPago(id, pago);
            return "Pago actualizado correctamente";
        }else{
            return "Pago no encontrado con id:"+id;
        }
    }
     
}
