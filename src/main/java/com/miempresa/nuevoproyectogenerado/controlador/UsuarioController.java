package com.miempresa.nuevoproyectogenerado.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.miempresa.nuevoproyectogenerado.dto.UsuarioDTO;
import com.miempresa.nuevoproyectogenerado.servicio.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuario/crear"; // Nombre de la vista para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute UsuarioDTO usuario) {
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuario"; // Redirige a la lista de entidades
    }

    @GetMapping("/{id}")
    public String mostrarUsuario(@PathVariable Long id, Model model) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuario = usuarioOptional.get();
		model.addAttribute("usuario", usuario);
        return "usuario/ver"; // Nombre de la vista para mostrar una entidad
        } else {
            model.addAttribute("mensajeError", "Usuario no encontrado");
            return "error"; // Nombre de una vista de error
        }
		
    }

    @GetMapping
    public String listarUsuario(Model model) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosUsuario();
        model.addAttribute("usuarios", usuarios);
        return "usuario/lista"; // Nombre de la vista para la lista de entidades
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<UsuarioDTO> usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuario/editar"; // Nombre de la vista para el formulario de edición
    }

    @PostMapping("/editar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute UsuarioDTO usuario) {
        usuarioService.actualizarUsuario(id, usuario);
        return "redirect:/usuario"; // Redirige a la lista de entidades
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuario"; // Redirige a la lista de entidades
    }
}