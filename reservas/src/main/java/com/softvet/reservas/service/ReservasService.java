package com.softvet.reservas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.softvet.reservas.dto.ReservaDetalleDTO;
import com.softvet.reservas.model.Notificaciones;
import com.softvet.reservas.model.Pago;
import com.softvet.reservas.model.Reserva;
import com.softvet.reservas.model.Servicio;
import com.softvet.reservas.repository.ReservasRepository;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository repository;

    public List<Reserva> listar(){
        return repository.findAll();
    }

    public Optional<Reserva> buscarPorId(Integer id){
        return repository.findById(id);
    }

public Reserva guardarReserva(Reserva reserva){
    Reserva nueva = repository.save(reserva);
    
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8088/notificaciones/agregar";
    
    Notificaciones notificacion = new Notificaciones();
    notificacion.setTitulo("Nueva reserva creada");
    notificacion.setMensaje("Se ha creado una reserva para el " + nueva.getFecha());
    notificacion.setTipo("RESERVA");
    notificacion.setDestinatario("cliente");
    notificacion.setEstado("PENDIENTE");
    
    restTemplate.postForObject(url, notificacion, Notificaciones.class);
    
    return nueva;
}

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }

    public Reserva actualizarReserva(Integer id, Reserva reserva){
        reserva.setId(id);
        return repository.save(reserva);
    }

    public ReservaDetalleDTO obtenerDetalle(Integer reservasId){
        Optional<Reserva> reservaOpt = repository.findById(reservasId);
        if(reservaOpt.isEmpty())
            return null;

        Reserva reserva = reservaOpt.get();
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8084/pago/" + reserva.getPagoId();
        Pago pago = restTemplate.getForObject(url, Pago.class);

        String urlServicio = "http://localhost:8082/servicios/" + reserva.getServicioId();
        Servicio servicio = restTemplate.getForObject(urlServicio, Servicio.class);

        ReservaDetalleDTO dto = new ReservaDetalleDTO();
        dto.setFecha(reserva.getFecha());
        dto.setHora(reserva.getHora());
        dto.setValor(pago.getValor());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setNombreServicio(servicio.getNombre());
        
        return dto;    
        }


}

