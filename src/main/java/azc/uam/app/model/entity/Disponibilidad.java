package azc.uam.app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "disponibilidad")
@Getter
@Setter
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int diaSemana; // 1=Lunes, ..., 7=Domingo

    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    private Date horaFin;

    private boolean disponible = true;

    public Disponibilidad() {}
}
