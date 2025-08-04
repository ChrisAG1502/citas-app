package azc.uam.app.controller;

import azc.uam.app.model.entity.Cita;
import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.service.CitaService;
import azc.uam.app.service.PublicacionService;
import azc.uam.app.service.persistance.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/dashboard/cliente")
public class PublicacionClienteController {

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/publicacion/{id}")
    public String mostrarFormularioAgendar(@PathVariable Long id, Model model) {
        Publicacion publicacion = publicacionService.obtenerPublicacion(id);
        model.addAttribute("publicaciones", publicacion);
        model.addAttribute("diasDisponibles", citaService.obtenerDiasConHoras());
        return "cita";
    }

    @PostMapping("/agendar-cita")
    public String agendarCita(
            @RequestParam("fecha") String fechaStr,
            @RequestParam("hora") String horaStr,
            @RequestParam("correoProfesional") String correoProfesional,
            Model model,
            Authentication authentication) {
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            LocalTime hora = LocalTime.parse(horaStr);
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            System.out.println("fechaStr: '" + fechaStr + "'");
            System.out.println("horaStr: '" + horaStr + "'");

            // Obtener cliente autenticado
            String correoUsuario = authentication.getName();
            Usuario cliente = usuarioService.findByCorreo(correoUsuario);

            //Profesiona
            Usuario profesional = usuarioService.findByCorreo(correoProfesional);

            if (profesional == null) {
                model.addAttribute("error", "Profesional no encontrado.");
                return "cita";
            }

            // Crear la cita
            Cita cita = new Cita();
            cita.setFechaHoraInicio(fechaHora);
            cita.setFechaHoraFin(fechaHora.plusMinutes(60)); // ejemplo: duración de 1 hora
            cita.setCliente(cliente);
            cita.setProfesional(profesional);

            citaService.guardar(cita);

            model.addAttribute("mensaje", "Cita agendada correctamente.");
            return "redirect:/dashboard/cliente";

        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Formato de fecha u hora incorrecto.");
            System.out.println(e.getMessage());
            return "cita";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", "Error al agendar la cita: " + e.getMessage());
            return "cita";
        }
    }



}
