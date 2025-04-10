package com.miempresa.nuevoproyectogenerado.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.PedidoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("pedido", new PedidoDTO());
        return "pedido/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearPedido(@ModelAttribute PedidoDTO pedido) {
        pedidoService.crearPedido(pedido);
        return "redirect:/pedido"; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarPedido(@PathVariable Long id, Model model) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.obtenerPedidoPorId(id);
        if (pedidoOptional.isPresent()) {
            PedidoDTO pedido = pedidoOptional.get();
		model.addAttribute("pedido", pedido);
        return "pedido/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Pedido no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarPedido(Model model) {
        List<PedidoDTO> pedidos = pedidoService.obtenerTodosPedido();
        model.addAttribute("pedidos", pedidos);
        return "pedido/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<PedidoDTO> pedido = pedidoService.obtenerPedidoPorId(id);
        model.addAttribute("pedido", pedido);
        return "pedido/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarPedido(@PathVariable Long id, @ModelAttribute PedidoDTO pedido) {
        pedidoService.actualizarPedido(id, pedido);
        return "redirect:/pedido"; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedido"; // Redirige a la lista de entidades
    }
}