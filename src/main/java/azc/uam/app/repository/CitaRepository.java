package azc.uam.app.repository;

import azc.uam.app.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findyByClienteId(long clienteid);
    List<Cita> findyByProfesionistaId(long profesionistaId);
}
