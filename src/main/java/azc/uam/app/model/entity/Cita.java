package azc.uam.app.model.entity;

import azc.uam.app.model.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "citas")
@Getter
@Setter
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;

    private int duracionMinutos;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado = EstadoCita.PENDIENTE;

    private String tipoServicio;
    private String ubicacion;
    private String enlaceVirtual;

    @Lob
    private String notas;

    private Integer calificacionCliente;
    private Integer calificacionProfesional;

    @Lob
    private String comentariosCliente;

    @Lob
    private String comentariosProfesional;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = new Date();
    }

    public Cita(){}
}
