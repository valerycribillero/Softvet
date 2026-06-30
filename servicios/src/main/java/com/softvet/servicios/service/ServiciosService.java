package com.softvet.servicios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softvet.servicios.dto.ServiciosListadoDTO;
import com.softvet.servicios.model.Servicios;
import com.softvet.servicios.repository.ServiciosRepository;

@Service
public class ServiciosService {

    private final ServiciosRepository repository;

    public ServiciosService(ServiciosRepository repository) {
        this.repository = repository;
    }

    public List<Servicios> obtenerServicios() {
        return repository.findAll();
    }

    public Servicios guardarServicio(Servicios servicios) {
        return repository.save(servicios);
    }

    public Servicios obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminarServicio(Integer id) {
        repository.deleteById(id);
    }

    public Servicios actualizarServicio(Integer id, Servicios datos) {

        Servicios servicio = repository.findById(id).orElse(null);

        if (servicio == null) {
            return null;
        }

        servicio.setNombre(datos.getNombre());
        servicio.setTipo(datos.getTipo());
        servicio.setPrecio(datos.getPrecio());
        servicio.setDescripcion(datos.getDescripcion());

        return repository.save(servicio);
    }

    public List<Servicios> buscarPorTipo(String tipo) {
        return repository.findByTipoContainingIgnoreCase(tipo);
    }

    public List<ServiciosListadoDTO> listarDTO() {

        List<Servicios> servicios = repository.findAll();
        List<ServiciosListadoDTO> lista = new ArrayList<>();

        for (Servicios s : servicios) {

            ServiciosListadoDTO dto = new ServiciosListadoDTO();

            dto.setId(s.getId());
            dto.setNombre(s.getNombre());
            dto.setTipo(s.getTipo());
            dto.setPrecio(s.getPrecio());
            dto.setDescripcion(s.getDescripcion());

            lista.add(dto);
        }

        return lista;
    }
}