package com.miempresa.nuevoproyectogenerado.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.nuevoproyectogenerado.entidad.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}