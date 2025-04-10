package com.miempresa.nuevoproyectogenerado.servicio.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.repositorio.ClienteRepository;
import com.miempresa.nuevoproyectogenerado.servicio.ClienteService;
import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;
import com.miempresa.nuevoproyectogenerado.entidad.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    
    private final ClienteRepository clienteRepository;
	
	ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        return ClienteDTO.toDTO(clienteRepository.save(ClienteDTO.toDomain(clienteDTO)));
    }

    @Override
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).map(ClienteDTO::toDTO);
    }

    @Override
    public List<ClienteDTO> obtenerTodosCliente() {
        return clienteRepository.findAll().stream()
                .map(ClienteDTO::toDTO)
                .toList();
    }

    @Override
    public Optional<ClienteDTO> actualizarCliente(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    Cliente cliente = ClienteDTO.toDomain(clienteDTO);
                    cliente.setId(id);
                    return clienteRepository.save(cliente);
                })
                .map(ClienteDTO::toDTO);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}