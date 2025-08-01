package azc.uam.app.service;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements azc.uam.app.service.persistance.UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional
    public void save(UsuarioDTO usuarioDTO) {
        // Convertir DTO a Entidad
        Usuario usuario = new Usuario();

        // Mapear campos
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setContrasenia(usuarioDTO.getContrasenia()); // Ya viene encriptada
        usuario.setActivo(true);

        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}
