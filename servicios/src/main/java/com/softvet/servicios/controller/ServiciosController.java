package com.softvet.servicios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.softvet.servicios.dto.ServiciosListadoDTO;
import com.softvet.servicios.model.Servicios;
import com.softvet.servicios.service.ServiciosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicios")
@Tag(name = "Servicios", description = "API de gestión de servicios veterinarios")
public class ServiciosController {

    private final ServiciosService service;

    public ServiciosController(ServiciosService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Servicio creado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/agregar")
    public ResponseEntity<Servicios> crearServicio(
            @Valid @RequestBody Servicios servicios) {

        Servicios nuevo = service.guardarServicio(servicios);

        return ResponseEntity.status(201).body(nuevo);
    }

    @Operation(summary = "Listar todos los servicios")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping("/listar")
    public List<Servicios> listarServicios() {
        return service.obtenerServicios();
    }

    @Operation(summary = "Buscar servicio por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio encontrado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Servicios> obtenerPorId(
            @PathVariable Integer id) {

        Servicios servicio = service.obtenerPorId(id);

        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicio);
    }

    @Operation(summary = "Buscar servicios por tipo")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada")
    @GetMapping("/buscar")
    public List<Servicios> buscarPorTipo(
            @RequestParam String tipo) {

        return service.buscarPorTipo(tipo);
    }

    @Operation(summary = "Actualizar un servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio actualizado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Servicios> actualizarServicio(
            @PathVariable Integer id,
            @Valid @RequestBody Servicios servicios) {

        Servicios actualizado =
                service.actualizarServicio(id, servicios);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Servicio eliminado"),
        @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarServicio(
            @PathVariable Integer id) {

        Servicios servicio = service.obtenerPorId(id);

        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminarServicio(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar servicios mediante DTO")
    @ApiResponse(responseCode = "200", description = "DTO obtenido correctamente")
    @GetMapping("/listar-dto")
    public List<ServiciosListadoDTO> listarDTO() {
        return service.listarDTO();
    }
}

// GET listar  hay que cambiar todo
/* 
    @Operation(
        summary = "Listar",
        descripction = "Obtiene una lista con todas las películas registradas en el sistema"    
)  
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "lista obtenida correctamente")
})
    @GetMapping
    public List <Pelicula> listar() {
        return service.listar();
    }
    
*/    
// POST listar  hay que cambiar todo
/* 
    @Operation(
        summary = "Registrar pelicula",
        descripction = "Permite registrar una nueva pelicula en el catalogo"    
)  
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "lista obtenida correctamente")
})
    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return service.guardarPelicula(pelicula);
    }
    
*/    

