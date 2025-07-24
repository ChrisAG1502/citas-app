package azc.uam.app.repository;

import azc.uam.app.model.entity.Giro;

import java.util.Optional;

public interface GiroRepository {
    Optional<Giro> findByNombreContainingIgnoreCase(String nombre);
}
