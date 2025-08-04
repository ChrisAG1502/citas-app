package azc.uam.app.controller;

import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class PublicacionClienteController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("anuncio/{id}")
    public String verPublicacion(@PathVariable long id, Model model) {
        Publicacion publicacion = publicacionService.obtenerPublicacion(id);
        publicacionService.incrementarVistas(id);

        model.addAttribute("publicacion", publicacion);
        return "publicacion";
    }
}
