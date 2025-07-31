package azc.uam.app.controller;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.TipoUsuario;
import azc.uam.app.service.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        model.addAttribute("tipoUsuario", TipoUsuario.values());
        return "registro";
    }

    @PostMapping
    public String registration(
            @Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        log.info("Datos recibidos: nombre={}, correo={}, tipo={}",
                usuarioDTO.getNombre(), usuarioDTO.getCorreo(), usuarioDTO.getTipoUsuario());

        // Validar si el correo ya existe
        Usuario usuarioExistente = usuarioService.findByCorreo(usuarioDTO.getCorreo());
        if (usuarioExistente != null) {
            result.rejectValue("correo", "error.usuario", "El correo ya está registrado");
        }

        // Validar selección de tipo de usuario
        if (usuarioDTO.getTipoUsuario() == null) {
            result.rejectValue("tipoUsuario", "error.usuario", "Debe seleccionar un tipo de usuario");
        }

        if (result.hasErrors()) {
            model.addAttribute("tiposUsuario", TipoUsuario.values());
            return "registro";
        }

        try {
            // Encriptar contraseña antes de guardar
            usuarioDTO.setContrasenia(passwordEncoder.encode(usuarioDTO.getContrasenia()));

            // Guardar usando el DTO directamente
            usuarioService.save(usuarioDTO);

            redirectAttributes.addFlashAttribute("success", "Registro exitoso. Por favor inicie sesión.");
            return "redirect:/success";

        } catch (Exception e) {
            log.error("Error al registrar usuario: {}", e.getMessage(), e);
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            model.addAttribute("tiposUsuario", TipoUsuario.values());
            return "register";
        }
    }
}
