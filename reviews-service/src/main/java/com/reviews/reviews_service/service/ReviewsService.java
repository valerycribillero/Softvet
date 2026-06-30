package com.reviews.reviews_service.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reviews.reviews_service.dto.ReviewsDetalleDTO;
import com.reviews.reviews_service.dto.ReviewsListadoDTO;
import com.reviews.reviews_service.model.Notificaciones;
import com.reviews.reviews_service.model.Reviews;
import com.reviews.reviews_service.model.Usuario;
import com.reviews.reviews_service.repository.ReviewsRepository;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public List<Reviews> obtenerReview() {
        return reviewsRepository.findAll();
    }
    public Reviews obtenerPorId(Integer id) {
        return reviewsRepository.findById(id).orElse(null);
    }

    public Reviews guardaReviews(Reviews reviews){
        Reviews nueva = reviewsRepository.save(reviews);
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/notificaciones/agregar";
        
        Notificaciones notificacion = new Notificaciones();
        notificacion.setTitulo("Nueva reseña recibida");
        notificacion.setMensaje("Se ha recibido una reseña con calificación " + nueva.getCalificacion());
        notificacion.setTipo("RESEÑA");
        notificacion.setDestinatario("cliente");
        notificacion.setEstado("PENDIENTE");
        
        restTemplate.postForObject(url, notificacion, Notificaciones.class);
        
        return nueva;
    }

    public void eliminarReview(Integer id) {
        reviewsRepository.deleteById(id);
    }
    
    public Reviews actualizarReview(Integer id, Reviews reviews) {
        reviews.setId(id);
        return reviewsRepository.save(reviews);
    }

    public List<ReviewsListadoDTO> listarDTO(){
        List<Reviews> reviews = reviewsRepository.findAll();
        List <ReviewsListadoDTO> listar = new ArrayList<>();

        for(Reviews r: reviews) {
            ReviewsListadoDTO dto = new ReviewsListadoDTO();
            dto.setMensaje(r.getMensaje());
            dto.setCalificacion(r.getCalificacion());
            dto.setUsuarioId(r.getUsuarioId());
            listar.add(dto);
        }
        return listar;
    }

    public ReviewsDetalleDTO obtenerDetalle(Integer reviewsId) {
        Optional<Reviews> reviewsOpt = reviewsRepository.findById(reviewsId);
        if (reviewsOpt.isEmpty()) return null;

        Reviews reviews = reviewsOpt.get();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/registro/usuario/" +  reviews.getUsuarioId();
        Usuario usuario = restTemplate.getForObject(url, Usuario.class);

        ReviewsDetalleDTO dto = new ReviewsDetalleDTO();
        dto.setNombreUsuario(usuario.getNombre());
        dto.setApellidoUsuario(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        dto.setMensaje(reviews.getMensaje());
        dto.setCalificacion(reviews.getCalificacion());
        return dto;

    }
}

