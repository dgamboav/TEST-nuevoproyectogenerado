package com.miempresa.nuevoproyectogenerado.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.ClienteDTO;
import com.miempresa.nuevoproyectogenerado.servicio.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new ClienteDTO());
        return "cliente/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute ClienteDTO cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/cliente"; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarCliente(@PathVariable Long id, Model model) {
        Optional<ClienteDTO> clienteOptional = clienteService.obtenerClientePorId(id);
        if (clienteOptional.isPresent()) {
            ClienteDTO cliente = clienteOptional.get();
		model.addAttribute("cliente", cliente);
        return "cliente/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Cliente no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarCliente(Model model) {
        List<ClienteDTO> clientes = clienteService.obtenerTodosCliente();
        model.addAttribute("clientes", clientes);
        return "cliente/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<ClienteDTO> cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute ClienteDTO cliente) {
        clienteService.actualizarCliente(id, cliente);
        return "redirect:/cliente"; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/cliente"; // Redirige a la lista de entidades
    }
}