package com.miempresa.nuevoproyectogenerado.servicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;

public interface PedidoService {

    PedidoDTO crearPedido(PedidoDTO pedidoDTO);

    Optional<PedidoDTO> obtenerPedidoPorId(Long id);

    List<PedidoDTO> obtenerTodosPedido();

    Optional<PedidoDTO> actualizarPedido(Long id, PedidoDTO pedidoDTO);

    void eliminarPedido(Long id);
}