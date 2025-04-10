package com.miempresa.nuevoproyectogenerado.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

import com.miempresa.nuevoproyectogenerado.entidad.Carrito;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoDTO {

    private Long id;

    private Long clienteId;

    private Date fechaCreacion;


    public static CarritoDTO toDTO(Carrito carrito) {
        return CarritoDTO.builder()
            .id(carrito.getId())
            .clienteId(carrito.getClienteId())
            .fechaCreacion(carrito.getFechaCreacion())
            .build();
    }

    public static Carrito toDomain(CarritoDTO carritoDTO) {
        return Carrito.builder()
            .id(carritoDTO.getId())
            .clienteId(carritoDTO.getClienteId())
            .fechaCreacion(carritoDTO.getFechaCreacion())
            .build();
    }
}