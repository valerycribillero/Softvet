package com.veterinaria.mascota.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.mascota.dto.MascotaListadoDTO;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> obtenerMascotas() {
        return mascotaRepository.findAll();
    }

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota obtenerPorId(Integer id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public void eliminarMascota(Integer id) {
        mascotaRepository.deleteById(id);
    }

    public Mascota actualizarMascota(Integer id, Mascota mascota) {
        mascota.setId(id);
        return mascotaRepository.save(mascota);
    }

    public List<MascotaListadoDTO> listarDTO() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        List<MascotaListadoDTO> lista = new ArrayList<>();

        for (Mascota m : mascotas) {
            MascotaListadoDTO dto = new MascotaListadoDTO();
            dto.setNombre(m.getNombre());
            dto.setEspecie(m.getEspecie());
            dto.setSexo(m.getSexo());
            dto.setEdad(m.getEdad());

            lista.add(dto);
        }

        return lista;
    }
}