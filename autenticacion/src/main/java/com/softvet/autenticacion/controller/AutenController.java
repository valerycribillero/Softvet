package com.softvet.autenticacion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softvet.autenticacion.DTO.UsuarioListadoDTO;
import com.softvet.autenticacion.model.Usuario;
import com.softvet.autenticacion.service.AutenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/registro")
public class AutenController {

    private final AutenService service;

    public AutenController(AutenService service){
        this.service = service;
    }

    @Operation(
        summary = "Listar",
        description = "Obtiene todos los usuarios registrados")  
    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")})

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }
    

    @Operation(
        summary = "Registrar usuario",
        description = "Permite registrar un nuevo usuario a la web"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
        @ApiResponse(responseCode = "201", description = "Se guardaron los datos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")

})
    @PostMapping("/agregar")
    public ResponseEntity<Usuario> crearPersona(@RequestBody Usuario usuario) {
        Usuario nuevo = service.guardarUsuario(usuario);
        return ResponseEntity.status(201).body(nuevo);
    }

    @Operation(
        summary = "Buscar usuario por ID",
        description = "Permite buscar un usuario con su id"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")

})
    @GetMapping("/usuario/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Permite eliminar un usuario de la base de datos"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")

})
    @DeleteMapping("/eliminar/{id}")
    public String eliminar (@PathVariable Integer id){
        Optional<Usuario> usuario = service.buscarPorId(id);
        if (usuario.isPresent()){
            service.eliminarPorId(id);
            return "El usuario fue eliminado correctamente";
        }else{
            return "No se encuentra usuario con id:"+id;
        }
    }

    @Operation(
        summary = "Actualizar usuario",
        description = "Permite actulizar datos de un usuario ya registrado"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos actualizados correctamente"),
        @ApiResponse(responseCode = "201", description = "Se guardaron los datos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")

})
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> existente = service.buscarPorId(id);
        if(existente.isPresent()){
            service.actualizarUsuario(id, usuario);
            return "Usuario actualizado correctamente";
        }else{
            return "No fue encontrado un usuario con id: "+id;
        }
    }

    @Operation(
        summary = "Listar con DTO",
        description = "Permite listar datos seleccionados sobre los usuarios registrados"    
)  
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos ingresados incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")

})
    @GetMapping("/listar-dto")
    public List<UsuarioListadoDTO> listarDTO(){
        return service.listarDTO();
    }    
}
