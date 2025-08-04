package azc.uam.app.service;

import azc.uam.app.dto.PublicacionDTO;
import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.repository.PublicacionRepository;
import azc.uam.app.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PublicacionService {


    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Publicacion crearPublicacion(PublicacionDTO publicacionDTO, String correoUsuario) throws Exception {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setCategoria(publicacionDTO.getCategoria());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setPrecio(publicacionDTO.getPrecio());

        // Buscar al usuario por correo y asignarlo
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        publicacion.setUsuario(usuario);

        // Guardar imágenes (tu código actual)
        String nombrePrimeraImagen = guardarImagen(publicacionDTO.getImagenPrincipal());
        publicacion.setImagenPrincipal(nombrePrimeraImagen);

        if (publicacionDTO.getImagenesAdicionales() != null) {
            for (MultipartFile imagen : publicacionDTO.getImagenesAdicionales()) {
                String nombreImagen = guardarImagen(imagen);
                publicacion.agregarImagenAdicional(nombreImagen);
            }
        }

        return publicacionRepository.save(publicacion);
    }

    public String guardarImagen(MultipartFile imagen) throws IOException {
        // Validación mejorada
        if (imagen == null || imagen.isEmpty() || imagen.getSize() == 0) {
            return null; // O maneja este caso como prefieras
        }

        // Validar nombre y extensión
        String nombreOriginal = StringUtils.cleanPath(imagen.getOriginalFilename());
        if (nombreOriginal.contains("..")) {
            throw new IllegalArgumentException("Nombre de archivo no válido");
        }

        String extension = FilenameUtils.getExtension(nombreOriginal);
        if (extension == null || !Arrays.asList("jpg", "jpeg", "png", "gif").contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("Formato de imagen no válido");
        }

        // Crear directorio si no existe
        Path uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generar nombre único
        String nombreArchivo = UUID.randomUUID().toString() + "." + extension;
        Path rutaDestino = uploadPath.resolve(nombreArchivo);

        // Guardar archivo
        imagen.transferTo(rutaDestino);

        return nombreArchivo;
    }

    public Publicacion obtenerPublicacion(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public void eliminarPublicacion(Long id) {
        publicacionRepository.deleteById(id);
    }

    public void incrementarVistas(long id){
        Publicacion publicacion = obtenerPublicacion(id);
        publicacion.setVistas(publicacion.getVistas() + 1);
        publicacionRepository.save(publicacion);
    }

    public long contarPublicacionesPorUsuario(String correo) {
        return publicacionRepository.countByUsuarioCorreo(correo);
    }

    public long contarVistasTotalesPorUsuario(String correo) {
        return publicacionRepository.sumVistasByUsuarioCorreo(correo);
    }

    public Publicacion obtenerPublicacionMasVistaPorUsuario(String correo) {
        return publicacionRepository.findTopByUsuarioCorreoOrderByVistasDesc(correo)
                .orElse(null);
    }

    public List<Publicacion> obtenerPublicacionesPorUsuario(String correo) {
        return publicacionRepository.findByUsuarioCorreoOrderByFechaCreacionDesc(correo);
    }

}
