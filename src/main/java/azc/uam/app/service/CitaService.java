package azc.uam.app.service;

import azc.uam.app.model.entity.Cita;
import azc.uam.app.repository.CitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final EmailService emailService;

    public CitaService(CitaRepository citaRepository, EmailService emailService) {
        this.citaRepository = citaRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Cita guardar(Cita cita) {
        // Guardar la cita en BD
        Cita citaGuardada = citaRepository.save(cita);

        // Preparar datos para el correo
        String destinatario = citaGuardada.getCliente().getCorreo();
        String nombreCliente = citaGuardada.getCliente().getNombre();
        String fechaHora = citaGuardada.getFechaHoraInicio()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm"));

        String asunto = "Confirmación de cita";
        String mensaje = "Hola " + nombreCliente + ",\n\n" +
                "Tu cita ha sido confirmada para el " + fechaHora + ".\n\n" +
                "¡Gracias por usar nuestro servicio!";

        // Enviar correo de confirmación
        emailService.enviarCorreoSimple(destinatario, asunto, mensaje);

        return citaGuardada;
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public void eliminarPorId(Long id) {
        citaRepository.deleteById(id);
    }

    public Map<LocalDate, List<LocalTime>> obtenerDiasConHoras() {
        Map<LocalDate, List<LocalTime>> diasConHoras = new LinkedHashMap<>();

        LocalDate hoy = LocalDate.now();
        int diasAgregados = 0;
        int diasObjetivo = 4; // Mostrar 4 días

        while (diasAgregados < diasObjetivo) {
            // Excluir sábados y domingos
            if (!(hoy.getDayOfWeek() == DayOfWeek.SATURDAY || hoy.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                List<LocalTime> horas = new ArrayList<>();

                // Horas desde las 10:00 a 17:00 (inclusive)
                for (int hora = 10; hora <= 17; hora++) {
                    horas.add(LocalTime.of(hora, 0));
                }

                diasConHoras.put(hoy, horas);
                diasAgregados++;
            }
            hoy = hoy.plusDays(1);
        }

        return diasConHoras;
    }

    public List<Cita> buscarCitasEntre(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findByFechaHoraInicioBetween(inicio, fin);
    }
}