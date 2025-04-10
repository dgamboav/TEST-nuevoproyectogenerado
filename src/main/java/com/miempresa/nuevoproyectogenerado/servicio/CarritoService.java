package com.miempresa.nuevoproyectogenerado.servicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;

public interface CarritoService {

    CarritoDTO crearCarrito(CarritoDTO carritoDTO);

    Optional<CarritoDTO> obtenerCarritoPorId(Long id);

    List<CarritoDTO> obtenerTodosCarrito();

    Optional<CarritoDTO> actualizarCarrito(Long id, CarritoDTO carritoDTO);

    void eliminarCarrito(Long id);
}