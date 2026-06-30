package com.softvet.pagos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.softvet.pagos.model.Notificaciones;
import com.softvet.pagos.model.Pago;
import com.softvet.pagos.repository.PagosRepository;

@Service
public class PagosService {

    @Autowired
    private PagosRepository repository;

    public List<Pago> listar(){
        return repository.findAll();
    }

    public Optional<Pago> buscarPorId(Integer id){
        return repository.findById(id);
    }

    public Pago guardarPago(Pago pago){
        Pago nuevo = repository.save(pago);
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/notificaciones/agregar";
        
        Notificaciones notificacion = new Notificaciones();
        notificacion.setTitulo("Pago registrado");
        notificacion.setMensaje("Se ha registrado un pago de " + nuevo.getValor() + " con método " + nuevo.getMetodoPago());
        notificacion.setTipo("PAGO");
        notificacion.setDestinatario("cliente");
        notificacion.setEstado("PENDIENTE");
        
        restTemplate.postForObject(url, notificacion, Notificaciones.class);
        
        return nuevo;
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }

    public Pago actualizarPago(Integer id, Pago pago){
        pago.setId(id);
        return repository.save(pago);
    }

}
