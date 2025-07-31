package azc.uam.app.service.persistance;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Usuario;

public interface UsuarioService {
    void save(UsuarioDTO userDTO);
    Usuario findByCorreo(String correo);
}
