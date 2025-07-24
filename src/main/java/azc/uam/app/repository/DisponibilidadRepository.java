package azc.uam.app.repository;

import azc.uam.app.model.entity.Disponibilidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisponibilidadRepository {
    List<Disponibilidad> findByProfesionistaId(Long profesionistaId);

    @Query("SELECT d FROM Disponibilidad d WHERE d.diaSemana = :dia")
    List<Disponibilidad> findByDiaSemana(@Param("dia") int diaSemana);
}
