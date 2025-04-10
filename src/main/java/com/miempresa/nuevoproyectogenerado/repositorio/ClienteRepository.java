package com.miempresa.nuevoproyectogenerado.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.nuevoproyectogenerado.entidad.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}