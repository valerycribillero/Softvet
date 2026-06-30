package com.softvet.servicios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softvet.servicios.model.Servicios;
import com.softvet.servicios.repository.ServiciosRepository;

@ExtendWith(MockitoExtension.class)
class ServiciosServiceTest {

    @Mock
    private ServiciosRepository serviciosRepository;

    @InjectMocks
    private ServiciosService serviciosService;

    @Test
    void obtenerServicios() {

        Servicios servicio = new Servicios();

        servicio.setId(1);
        servicio.setNombre("Consulta General");
        servicio.setTipo("Consulta");
        servicio.setPrecio(15000.0);
        servicio.setDescripcion("Consulta veterinaria");

        when(serviciosRepository.findAll())
                .thenReturn(Arrays.asList(servicio));

        List<Servicios> lista = serviciosService.obtenerServicios();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Consulta General", lista.get(0).getNombre());
        assertEquals("Consulta", lista.get(0).getTipo());
    }
}