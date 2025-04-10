package com.miempresa.nuevoproyectogenerado.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.*;

import com.miempresa.nuevoproyectogenerado.entidad.Pedido;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;

    private Long clienteId;

    private Date fechaPedido;

    private Double total;


    public static PedidoDTO toDTO(Pedido pedido) {
        return PedidoDTO.builder()
            .id(pedido.getId())
            .clienteId(pedido.getClienteId())
            .fechaPedido(pedido.getFechaPedido())
            .total(pedido.getTotal())
            .build();
    }

    public static Pedido toDomain(PedidoDTO pedidoDTO) {
        return Pedido.builder()
            .id(pedidoDTO.getId())
            .clienteId(pedidoDTO.getClienteId())
            .fechaPedido(pedidoDTO.getFechaPedido())
            .total(pedidoDTO.getTotal())
            .build();
    }
}