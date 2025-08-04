package azc.uam.app.repository;

import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
    Usuario findByIdAndTipoUsuario(Long id, TipoUsuario tipoUsuario);
}
