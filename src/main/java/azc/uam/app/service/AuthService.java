package azc.uam.app.service;

import azc.uam.app.dto.LoginRequest;
import azc.uam.app.model.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public Usuario authenticate(LoginRequest request){
        Usuario usuario = new Usuario();
    }
}
