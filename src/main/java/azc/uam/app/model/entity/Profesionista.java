package azc.uam.app.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesionistas")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Profesionista extends Usuario {
    @ManyToOne
    @JoinColumn(name = "giro_id", nullable = false)
    private Giro giro;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String experiencia;

    @Column(name = "costo", precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "calificaciones_proemdio", precision = 3, scale = 2)
    private BigDecimal calificacionesProemdio = BigDecimal.ZERO;

    @OneToMany(mappedBy = "profesionista", cascade = CascadeType.ALL)
    private List<Servicio> servicios = new ArrayList<>();

    @OneToMany(mappedBy = "profesionista")
    private List<Disponibilidad> disponibilidades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private Ubicacion ubicacion;

    public Profesionista() {}

    public Giro getGiro() {
        return giro;
    }

    public void setGiro(Giro giro) {
        this.giro = giro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getCalificacionesProemdio() {
        return calificacionesProemdio;
    }

    public void setCalificacionesProemdio(BigDecimal calificacionesProemdio) {
        this.calificacionesProemdio = calificacionesProemdio;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Disponibilidad> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidad> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
