package azc.uam.app.repository;

import azc.uam.app.model.entity.Profesionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfesionistaRepostiory extends JpaRepository<ProfesionistaRepostiory, Long> {
    List<Profesionista> findByGiroId(Long giroId);

    @Query("SELECT p FROM Profesionista p WHERE p.giro.nombre = :giro AND p.ubicacion.ciudad = :ciudad")
    List<Profesionista> findByGiroYCiudad(String giro, String ciudad);
}
