package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.repositorio.CarritoRepository;
import com.miempresa.nuevoproyectogenerado.servicio.CarritoService;
import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Carrito;

@Service
public class CarritoServiceImpl implements CarritoService {

    
    private final CarritoRepository carritoRepository;
	
	CarritoServiceImpl(CarritoRepository carritoRepository){
        this.carritoRepository = carritoRepository;
    }

    @Override
    public CarritoDTO crearCarrito(CarritoDTO carritoDTO) {
        return CarritoDTO.toDTO(carritoRepository.save(CarritoDTO.toDomain(carritoDTO)));
    }

    @Override
    public Optional<CarritoDTO> obtenerCarritoPorId(Long id) {
        return carritoRepository.findById(id).map(CarritoDTO::toDTO);
    }

    @Override
    public List<CarritoDTO> obtenerTodosCarrito() {
        return carritoRepository.findAll().stream()
                .map(CarritoDTO::toDTO)
                .toList();
    }

    @Override
    public Optional<CarritoDTO> actualizarCarrito(Long id, CarritoDTO carritoDTO) {
        return carritoRepository.findById(id)
                .map(existingCarrito -> {
                    Carrito carrito = CarritoDTO.toDomain(carritoDTO);
                    carrito.setId(id);
                    return carritoRepository.save(carrito);
                })
                .map(CarritoDTO::toDTO);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
}