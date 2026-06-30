package com.softvet.autenticacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softvet.autenticacion.model.Usuario;



@Repository
public interface AutenRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findById(Integer id);

}
