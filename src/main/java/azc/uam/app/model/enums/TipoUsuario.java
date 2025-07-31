package azc.uam.app.model.enums;

import lombok.Getter;

@Getter
public enum TipoUsuario {
    CLIENTE("Cliente"),
    PROFESIONAL("Profesional");

    private final String nombre;

    TipoUsuario(String nombre) {
        this.nombre = nombre;
    }
}
