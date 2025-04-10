package com.miempresa.nuevoproyectogenerado.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.nuevoproyectogenerado.entidad.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}