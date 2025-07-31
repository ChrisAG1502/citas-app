package azc.uam.app.service;

import azc.uam.app.model.entity.Usuario;
import azc.uam.app.service.persistance.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByCorreo(correo);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
        }

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasenia())
                .authorities("ROLE_" + usuario.getTipoUsuario().name())
                .accountExpired(false)
                .accountLocked(!usuario.isActivo())
                .credentialsExpired(false)
                .disabled(!usuario.isActivo())
                .build();
    }
}
