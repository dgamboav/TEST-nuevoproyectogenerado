package com.miempresa.nuevoproyectogenerado.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.EmpleadoDTO;
import com.miempresa.nuevoproyectogenerado.servicio.EmpleadoService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("empleado", new EmpleadoDTO());
        return "empleado/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute EmpleadoDTO empleado) {
        empleadoService.crearEmpleado(empleado);
        return "redirect:/empleado"; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarEmpleado(@PathVariable Long id, Model model) {
        Optional<EmpleadoDTO> empleadoOptional = empleadoService.obtenerEmpleadoPorId(id);
        if (empleadoOptional.isPresent()) {
            EmpleadoDTO empleado = empleadoOptional.get();
		model.addAttribute("empleado", empleado);
        return "empleado/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Empleado no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarEmpleado(Model model) {
        List<EmpleadoDTO> empleados = empleadoService.obtenerTodosEmpleado();
        model.addAttribute("empleados", empleados);
        return "empleado/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<EmpleadoDTO> empleado = empleadoService.obtenerEmpleadoPorId(id);
        model.addAttribute("empleado", empleado);
        return "empleado/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute EmpleadoDTO empleado) {
        empleadoService.actualizarEmpleado(id, empleado);
        return "redirect:/empleado"; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleado"; // Redirige a la lista de entidades
    }
}