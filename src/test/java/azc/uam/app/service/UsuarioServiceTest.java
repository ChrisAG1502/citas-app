package azc.uam.app.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import azc.uam.app.dto.UsuarioDTO;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.TipoUsuario;
import azc.uam.app.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void testSaveCliente() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCorreo("test@gmail.com");
        usuarioDTO.setContrasenia("test");
        usuarioDTO.setTipoUsuario(TipoUsuario.CLIENTE);
        usuarioDTO.setNombre("test");

        usuarioService.save(usuarioDTO);
    }
}