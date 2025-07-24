package azc.uam.app.model.entity;

import azc.uam.app.model.enums.TipoUsuario;
import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(nullable = false, unique = true)
    protected String correo;

    @Column(name = "nombre_completo", nullable = false)
    protected String nombreCompleto;

    @Column(nullable = false)
    protected String contrasenia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected TipoUsuario tipo;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date fechaRegistro;

    @Column(name = "ultimo_login")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ultimoLogin;

    public Usuario() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
}

