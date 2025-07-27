package azc.uam.app.repository;

import azc.uam.app.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Versión correcta - busca por el ID del cliente a través de la relación
    List<Cita> findByCliente_Id(Long clienteId);

    // Alternativa equivalente
    List<Cita> findByClienteId(Long clienteId);
}
