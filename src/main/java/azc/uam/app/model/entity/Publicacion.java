package azc.uam.app.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuppressWarnings("SpellCheckingInspection") //Avoid typos
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, length = 500)
    private String imagenPrincipal;

    @ElementCollection
    @CollectionTable(
            name = "publicaciones_imagenes",
            joinColumns = @JoinColumn(name = "publicacion_id")
    )
    @Column(name = "imagen_url", length = 500)
    private List<String> imagenesAdicionales = new ArrayList<>();

    @Column(length = 500)
    private String categoria;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "publicado", nullable = false)
    private Boolean publicado = true;

    @Column(name = "vistas", nullable = false)
    private Long vistas = 0L;

    public String getPrimeraImagen() {
        return imagenPrincipal;
    }

    public List<String> getTodasLasImagenes() {
        List<String> todasImagenes = new ArrayList<>();
        todasImagenes.add(imagenPrincipal);
        if (imagenesAdicionales != null && !imagenesAdicionales.isEmpty()) {
            todasImagenes.addAll(imagenesAdicionales);
        }
        return todasImagenes;
    }
    public void agregarImagenAdicional(String imagenUrl) {
        if (imagenesAdicionales == null) {
            imagenesAdicionales = new ArrayList<>();
        }
        imagenesAdicionales.add(imagenUrl);
    }

    public boolean tieneImagenes() {
        return imagenPrincipal != null && !imagenPrincipal.trim().isEmpty();
    }


}
