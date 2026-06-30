package com.softvet.pagos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.softvet.pagos.dto.ErrorDto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

@Hidden
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> manejarErroresValidacion(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        ErrorDto errorDto = new ErrorDto(
                LocalDateTime.now(),
                400,
                "Error de validación",
                errores,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

    //Manejar errores desde base de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> manejarErroresBaseDatos(
            DataIntegrityViolationException ex,
            HttpServletRequest request){
        ErrorDto errorDto = new ErrorDto(
                LocalDateTime.now(),
                400,
                "Error dentro de la base de datos",
                null,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(errorDto);
    }

}
