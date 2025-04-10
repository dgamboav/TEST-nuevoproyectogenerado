package com.miempresa.nuevoproyectogenerado.servicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO crearCliente(ClienteDTO clienteDTO);

    Optional<ClienteDTO> obtenerClientePorId(Long id);

    List<ClienteDTO> obtenerTodosCliente();

    Optional<ClienteDTO> actualizarCliente(Long id, ClienteDTO clienteDTO);

    void eliminarCliente(Long id);
}