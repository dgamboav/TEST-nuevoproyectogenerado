package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.repositorio.PedidoRepository;
import com.miempresa.nuevoproyectogenerado.servicio.PedidoService;
import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService {

    
    private final PedidoRepository pedidoRepository;
	
	PedidoServiceImpl(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {
        return PedidoDTO.toDTO(pedidoRepository.save(PedidoDTO.toDomain(pedidoDTO)));
    }

    @Override
    public Optional<PedidoDTO> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).map(PedidoDTO::toDTO);
    }

    @Override
    public List<PedidoDTO> obtenerTodosPedido() {
        return pedidoRepository.findAll().stream()
                .map(PedidoDTO::toDTO)
                .toList();
    }

    @Override
    public Optional<PedidoDTO> actualizarPedido(Long id, PedidoDTO pedidoDTO) {
        return pedidoRepository.findById(id)
                .map(existingPedido -> {
                    Pedido pedido = PedidoDTO.toDomain(pedidoDTO);
                    pedido.setId(id);
                    return pedidoRepository.save(pedido);
                })
                .map(PedidoDTO::toDTO);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}