package azc.uam.app.model.enums;

import lombok.Getter;

@Getter
@SuppressWarnings("SpellCheckingInspection")
public enum Categoria {
    SALUD("Salud"),
    AUTOMOVIL("Automovil"),
    HOGAR("Hogar"),
    CUIDADO_PERSONAL("Cuidado Personal"),
    EDUCACION("Educacion"),
    FERRETERIA("Ferreteria");

    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

}
