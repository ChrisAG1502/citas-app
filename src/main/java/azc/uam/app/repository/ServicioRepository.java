package azc.uam.app.repository;

import azc.uam.app.model.entity.Servicio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ServicioRepository {
    List<Servicio> findByProfesionistaId(Long profesionistaId);

    @Query("SELECT s FROM Servicio s WHERE s.nombre LIKE %:nombre% AND s.precio <= :precioMax")
    List<Servicio> buscarPersonalizado(@Param("nombre") String nombre,
                                       @Param("precioMax") BigDecimal precioMax);
}
