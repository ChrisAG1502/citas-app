package azc.uam.app.model.enums;

import lombok.Getter;

@Getter
public enum Genero {
    MASCULINO("Masculino"),
    FEMENINO("Femenino");

    private String genero;

    Genero(String genero){
        this.genero = genero;
    }
}
