package azc.uam.app.controller;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.Genero;
import azc.uam.app.service.persistance.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UsuarioService usuarioService;

    public DashboardController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cliente")
    public String dashboardCliente(Authentication authentication, Model model) {
        String correo = authentication.getName();
        Usuario usuario = usuarioService.findByCorreo(correo);
        model.addAttribute("usuario", authentication.getName());
        model.addAttribute("customerName", usuario.getNombre());
        if(usuario.getGenero().equals(Genero.MASCULINO)) {
            model.addAttribute("gender", "Bienvenido ");
        }
        else{
            model.addAttribute("gender", "Bienvenida ");
        }
        return "dashboard-cliente";
    }

    @GetMapping("/profesional")
    public String dashboardProfesional(Authentication authentication, Model model) {
        model.addAttribute("usuario", authentication.getName());
        return "dashboard-profesional";
    }

}
