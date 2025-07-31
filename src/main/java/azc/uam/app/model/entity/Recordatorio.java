package azc.uam.app.model.entity;

import azc.uam.app.model.enums.EstadoRecordatorio;
import azc.uam.app.model.enums.MetodoRecordatorio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "recordatorios")
@Getter
@Setter
public class Recordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Enumerated(EnumType.STRING)
    private MetodoRecordatorio metodo;

    @Enumerated(EnumType.STRING)
    private EstadoRecordatorio estado = EstadoRecordatorio.PENDIENTE;

}
