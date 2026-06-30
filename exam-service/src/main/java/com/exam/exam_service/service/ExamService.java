package com.exam.exam_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exam.exam_service.dto.ExamDetalleDTO;
import com.exam.exam_service.dto.ExamListadoDTO;
import com.exam.exam_service.model.Exam;
import com.exam.exam_service.model.Mascota;
import com.exam.exam_service.model.Notificaciones;
import com.exam.exam_service.repository.ExamRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> obtenerExamenes(){
        return examRepository.findAll();
    }

    public Exam agregarExamen(Exam exam) {
        Exam nuevo = examRepository.save(exam);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8088/notificaciones/agregar";

        Notificaciones notificacion = new Notificaciones();
        notificacion.setTitulo("Examen registrado");
        notificacion.setMensaje("Se ha registrado un examen de: ");
        notificacion.setTipo("EXAMEN");
        notificacion.setDestinatario("cliente");
        notificacion.setEstado("PENDIENTE");

        restTemplate.postForObject(url, notificacion, Notificaciones.class);

        return nuevo;
    }
    
    public Exam obtenerPorId(Integer id) {
        return examRepository.findById(id).orElse(null);
    }

    public void eliminarExamen(Integer id) {
        examRepository.deleteById(id);
    }

    public Exam actualizarExamen(Integer id, Exam exam) {
        exam.setId(id);
        return examRepository.save(exam);
    }

    public List<ExamListadoDTO> listarDTO(){
        List<Exam> exams = examRepository.findAll();
        List<ExamListadoDTO> lista = new ArrayList<>();

        for(Exam e: exams) {
            ExamListadoDTO dto= new ExamListadoDTO();
            dto.setResultado(e.getResultado());
            dto.setMascotaId(e.getMascotaId());
            dto.setFecha(e.getFecha());
            lista.add(dto);
        }
        return lista;
    }
    public ExamDetalleDTO obtenerDetalle(Integer examId) {
        Optional<Exam> examOpt = examRepository.findById(examId);
        if (examOpt.isEmpty()) return null;
        
        Exam exam = examOpt.get();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/mascota/" + exam.getMascotaId();
        Mascota mascota = restTemplate.getForObject(url, Mascota.class);

        ExamDetalleDTO dto = new ExamDetalleDTO();
        dto.setNombreMascota(mascota.getNombre());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());
        dto.setResultado(exam.getResultado());
        dto.setFecha(exam.getFecha());

        return dto;
    }
}