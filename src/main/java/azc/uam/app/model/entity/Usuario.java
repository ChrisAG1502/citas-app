package azc.uam.app.model.entity;

import azc.uam.app.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario; // CLIENTE o PROFESIONAL

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro = new Date();

    private boolean activo = true;

    public Usuario() {}
}
