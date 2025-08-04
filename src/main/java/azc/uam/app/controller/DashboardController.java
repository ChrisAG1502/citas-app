package azc.uam.app.controller;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.Genero;
import azc.uam.app.service.PublicacionService;
import azc.uam.app.service.persistance.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private PublicacionService publicacionService;


    public DashboardController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cliente")
    public String dashboardCliente(Authentication authentication, Model model) {
        List<Publicacion> publicaciones = publicacionService.getAllPublicaciones();
        model.addAttribute("publicaciones", publicaciones);
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
        // Obtener el usuario autenticado
        String username = authentication.getName();
        Usuario usuario = usuarioService.findByCorreo(username);

        // Obtener estadísticas reales
        long totalPublicaciones = publicacionService.contarPublicacionesPorUsuario(username);
        long totalVistas = publicacionService.contarVistasTotalesPorUsuario(username);
        Publicacion publicacionMasVista = publicacionService.obtenerPublicacionMasVistaPorUsuario(username);

        // Agregar datos al modelo
        model.addAttribute("totalPublicaciones", totalPublicaciones);
        model.addAttribute("totalVistas", totalVistas);
        model.addAttribute("publicacionMasVista", publicacionMasVista != null ? publicacionMasVista.getTitulo() : "No hay anuncios");
        model.addAttribute("vistasPublicacionDestacada", publicacionMasVista != null ? publicacionMasVista.getVistas() : 0);

        model.addAttribute("usuario", authentication.getName());
        model.addAttribute("customerName", usuario.getNombre());
        if(usuario.getGenero().equals(Genero.MASCULINO)) {
            model.addAttribute("gender", "Bienvenido ");
        }
        else{
            model.addAttribute("gender", "Bienvenida ");
        }

        List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(username);
        model.addAttribute("publicaciones", publicaciones);

        return "dashboard-profesional";
    }

}
