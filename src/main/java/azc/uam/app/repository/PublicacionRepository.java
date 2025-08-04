package azc.uam.app.repository;

import azc.uam.app.model.entity.Publicacion;
import azc.uam.app.model.enums.Categoria;
import azc.uam.app.model.enums.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    // 1. Todas las publicaciones hechas por un usuario específico (por ID)
    List<Publicacion> findByUsuarioId(Long usuarioId);

    // 2. Todas las publicaciones de usuarios tipo PROFESIONAL
    @Query("SELECT p FROM Publicacion p WHERE p.usuario.tipoUsuario = :tipoUsuario")
    List<Publicacion> findAllByTipoUsuario(@Param("tipoUsuario") TipoUsuario tipoUsuario);

    // 3. Todas las publicaciones con un título que contenga un texto (ignora mayúsculas)
    List<Publicacion> findByTituloContainingIgnoreCase(String titulo);

    // 4. Publicaciones publicadas (booleano)
    List<Publicacion> findByPublicadoTrue();

    // 5. Publicaciones de un usuario profesional específico
    @Query("SELECT p FROM Publicacion p WHERE p.usuario.id = :usuarioId AND p.usuario.tipoUsuario = 'PROFESIONAL'")
    List<Publicacion> findPublicacionesDeProfesional(@Param("usuarioId") Long usuarioId);

    // Métodos corregidos para trabajar con el correo del usuario

    @Query("SELECT COUNT(p) FROM Publicacion p WHERE p.usuario.correo = :correo")
    long countByUsuarioCorreo(@Param("correo") String correo);

    @Query("SELECT COALESCE(SUM(p.vistas), 0) FROM Publicacion p WHERE p.usuario.correo = :correo")
    long sumVistasByUsuarioCorreo(@Param("correo") String correo);

    @Query("SELECT p FROM Publicacion p WHERE p.usuario.correo = :correo ORDER BY p.vistas DESC LIMIT 1")
    Optional<Publicacion> findTopByUsuarioCorreoOrderByVistasDesc(@Param("correo") String correo);

    @Query("SELECT p FROM Publicacion p WHERE p.usuario.correo = :correo ORDER BY p.fechaCreacion DESC")
    List<Publicacion> findByUsuarioCorreoOrderByFechaCreacionDesc(@Param("correo") String correo);

    // Versión paginada
    @Query("SELECT p FROM Publicacion p WHERE p.usuario.correo = :correo")
    Page<Publicacion> findByUsuarioCorreo(@Param("correo") String correo, Pageable pageable);

    // Métodos adicionales útiles

    @Query("SELECT p FROM Publicacion p WHERE p.categoria = :categoria AND p.publicado = true ORDER BY p.fechaCreacion DESC")
    List<Publicacion> findPublicacionesPorCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT p FROM Publicacion p WHERE p.usuario.correo = :correo AND p.publicado = :publicado")
    List<Publicacion> findByUsuarioCorreoAndPublicado(@Param("correo") String correo,
                                                      @Param("publicado") boolean publicado);
}