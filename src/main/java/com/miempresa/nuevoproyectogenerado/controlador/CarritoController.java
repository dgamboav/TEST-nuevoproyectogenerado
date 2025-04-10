package com.miempresa.nuevoproyectogenerado.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.CarritoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.CarritoService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("carrito", new CarritoDTO());
        return "carrito/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearCarrito(@ModelAttribute CarritoDTO carrito) {
        carritoService.crearCarrito(carrito);
        return "redirect:/carrito"; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarCarrito(@PathVariable Long id, Model model) {
        Optional<CarritoDTO> carritoOptional = carritoService.obtenerCarritoPorId(id);
        if (carritoOptional.isPresent()) {
            CarritoDTO carrito = carritoOptional.get();
		model.addAttribute("carrito", carrito);
        return "carrito/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Carrito no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarCarrito(Model model) {
        List<CarritoDTO> carritos = carritoService.obtenerTodosCarrito();
        model.addAttribute("carritos", carritos);
        return "carrito/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<CarritoDTO> carrito = carritoService.obtenerCarritoPorId(id);
        model.addAttribute("carrito", carrito);
        return "carrito/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarCarrito(@PathVariable Long id, @ModelAttribute CarritoDTO carrito) {
        carritoService.actualizarCarrito(id, carrito);
        return "redirect:/carrito"; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return "redirect:/carrito"; // Redirige a la lista de entidades
    }
}