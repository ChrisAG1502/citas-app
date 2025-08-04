package azc.uam.app.dto;

import azc.uam.app.model.enums.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class PublicacionDTO {
    @NotBlank(message = "El título del anuncio es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción del anuncio es obligatoria")
    private String descripcion;

    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precio;

    @NotNull(message = "Debes subir al menos una imagen")
    private MultipartFile imagenPrincipal;

    private List<MultipartFile> imagenesAdicionales;
}

