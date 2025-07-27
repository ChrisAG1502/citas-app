package azc.uam.app.repository;

import azc.uam.app.model.entity.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {
    List<Disponibilidad> findByProfesionistaId(Long profesionistaId);

    @Query("SELECT d FROM Disponibilidad d WHERE d.diaSemana = :dia")
    List<Disponibilidad> findByDiaSemana(@Param("dia") int diaSemana);
}
