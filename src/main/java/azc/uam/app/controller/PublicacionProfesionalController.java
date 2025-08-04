package azc.uam.app.controller;

import azc.uam.app.dto.PublicacionDTO;
import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/dashboard/profesional/anuncios")
public class PublicacionProfesionalController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/nuevo")
    public String nuevoPublicacion(Model model) {
        model.addAttribute("publicacion", new PublicacionDTO());
        return "nueva-publicacion";
    }

    @PostMapping("/guardar")
    public String guardarPublicacion(
            @RequestParam("imagenPrincipal") MultipartFile imagenPrincipal,
            @RequestParam(value = "imagenesAdicionales", required = false) MultipartFile[] imagenesAdicionales,
            @Valid @ModelAttribute("publicacion") PublicacionDTO publicacionDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Authentication authentication // Spring Security inyecta el usuario autenticado
    ) throws IOException {
        String nombre = imagenPrincipal.getOriginalFilename();

        if(imagenesAdicionales != null && imagenesAdicionales.length > 0) {
            for(MultipartFile imagen : imagenesAdicionales) {
                if(!imagen.isEmpty()) {
                    publicacionService.guardarImagen(imagen);
                }
            }
        }

        if (result.hasErrors()) {
            return "nueva-publicacion";
        }

        try {
            // Obtener el correo del usuario autenticado
            String correoUsuario = authentication.getName();

            // Crear la publicación asociada al usuario
            Publicacion publicacion = publicacionService.crearPublicacion(publicacionDTO, correoUsuario);

            redirectAttributes.addFlashAttribute("success", "Anuncio creado exitosamente!");
            return "redirect:/dashboard/profesional";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el anuncio: " + e.getMessage());
            System.out.println("Error al guardar el anuncio: " + e.getMessage());
            return "redirect:/dashboard/profesional/anuncios/nuevo";
        }
    }

    @GetMapping("/{id}")
    public String verAnuncio(@PathVariable Long id, Model model) {
        Publicacion publicacion = publicacionService.obtenerPublicacion(id);
        if(publicacion == null) {
            return "redirect:/dashboard/profesional";
        }
        model.addAttribute("publicacion", publicacion);
        return "detalle-anuncio";
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody // Añade esta anotación para que devuelva una respuesta HTTP
    public ResponseEntity<String> eliminarPublicacion(@PathVariable Long id) {
        try {
            publicacionService.eliminarPublicacion(id);
            return ResponseEntity.ok("Anuncio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el anuncio: " + e.getMessage());
        }
    }
}