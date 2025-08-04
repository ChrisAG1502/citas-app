package azc.uam.app.scheduler;

import azc.uam.app.model.entity.Cita;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.service.CitaService;
import azc.uam.app.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RecordatorioCitaScheduler {

    private final CitaService citaService;
    private final EmailService emailService;

    public RecordatorioCitaScheduler(CitaService citaService, EmailService emailService) {
        this.citaService = citaService;
        this.emailService = emailService;
    }

    // Se ejecuta todos los días a las 8am (puedes ajustar)
    @Scheduled(cron = "0 0 8 * * *")
    public void enviarRecordatorios() {
        LocalDate mañana = LocalDate.now().plusDays(1);
        LocalDateTime inicio = LocalDateTime.of(mañana, LocalTime.MIN);
        LocalDateTime fin = LocalDateTime.of(mañana, LocalTime.MAX);

        // Obtener todas las citas del día siguiente (necesitas un método en CitaService/repository)
        List<Cita> citasManana = citaService.buscarCitasEntre(inicio, fin);

        for (Cita cita : citasManana) {
            Usuario usuario = cita.getCliente();
            String correo = usuario.getCorreo();
            String asunto = "Recordatorio de cita para mañana";
            String texto = "Hola " + usuario.getNombre() + ",\n\n" +
                    "Te recordamos que tienes una cita programada para mañana " +
                    cita.getFechaHoraInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm")) + ".\n\n" +
                    "¡No olvides asistir!";
            emailService.enviarCorreoSimple(correo, asunto, texto);
        }
    }
}
