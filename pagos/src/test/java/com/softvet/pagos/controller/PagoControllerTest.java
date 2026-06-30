package com.softvet.pagos.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.softvet.pagos.model.Pago;
import com.softvet.pagos.service.PagosService;

@WebMvcTest(PagosController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagosService service;

    @Test
    void listar() throws Exception {
        List<Pago> pagos = List.of(
                new Pago(1, 12000, "Efectivo")
        );

        when(service.listar()).thenReturn(pagos);

        mockMvc.perform(get("/pago"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].metodoPago").value("Efectivo"));
    }

    @Test
    void buscarPorId() throws Exception {
        Pago pago = new Pago(1, 12000, "Efectivo");

        when(service.buscarPorId(1)).thenReturn(Optional.of(pago));

        mockMvc.perform(get("/pago/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metodoPago").value("Efectivo"));
    }

    @Test
    void guardarPago() throws Exception {
        String pagoJson = """
                {
                    "valor": 12000,
                    "metodoPago": "Efectivo"
                }
                """;

        Pago pagoCreado = new Pago(2, 12000, "Efectivo");

        when(service.guardarPago(any(Pago.class))).thenReturn(pagoCreado);

        mockMvc.perform(post("/pago/agregar")
                .contentType("application/json")
                .content(pagoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.metodoPago").value("Efectivo"));
    }

    @Test
    void eliminarPago() throws Exception {
        Pago pago = new Pago(1, 12000, "Efectivo");

        when(service.buscarPorId(1)).thenReturn(Optional.of(pago));
        doNothing().when(service).eliminarPorId(1);

        mockMvc.perform(delete("/pago/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pago eliminado exitosamente"));
    }

    @Test
    void actualizarPago() throws Exception {
        String pagoJson = """
                {
                    "valor": 25000,
                    "metodoPago": "Mercado pago"
                }
                """;

        Pago pagoExistente = new Pago(1, 12000, "Efectivo");

        when(service.buscarPorId(1)).thenReturn(Optional.of(pagoExistente));
        when(service.actualizarPago(eq(1), any(Pago.class)))
                .thenReturn(new Pago(1, 25000, "Mercado pago"));

        mockMvc.perform(put("/pago/actualizar/1")
                .contentType("application/json")
                .content(pagoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Pago actualizado correctamente"));
    }
}