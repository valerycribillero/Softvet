package com.softvet.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softvet.pagos.model.Pago;

public interface PagosRepository extends JpaRepository<Pago, Integer>{

}
