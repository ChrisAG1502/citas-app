package azc.uam.app.model.entity;

import azc.uam.app.model.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id", nullable = false)
    private Usuario profesional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @Column(nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Constructor
    public Cita() {
        this.estado = EstadoCita.PENDIENTE;
    }

    // Método para verificar superposición de horarios
    public boolean esSuperpuesta(LocalDateTime inicio, LocalDateTime fin) {
        return this.fechaHoraInicio.isBefore(fin) && this.fechaHoraFin.isAfter(inicio);
    }
}
