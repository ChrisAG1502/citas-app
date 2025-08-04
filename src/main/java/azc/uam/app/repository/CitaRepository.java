package azc.uam.app.repository;

import azc.uam.app.model.entity.Cita;
import azc.uam.app.model.entity.Usuario;
import azc.uam.app.model.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Buscar citas por profesional
    List<Cita> findByProfesionalId(Long profesionalId);

    // Buscar citas por cliente
    List<Cita> findByClienteId(Long clienteId);

    // Buscar citas por estado
    List<Cita> findByEstado(EstadoCita estado);

    // Buscar citas futuras de un profesional
    List<Cita> findByProfesionalIdAndFechaHoraInicioAfter(Long profesionalId, LocalDateTime fecha);

    // Buscar citas futuras de un cliente
    List<Cita> findByClienteIdAndFechaHoraInicioAfter(Long clienteId, LocalDateTime fecha);

    // Verificar disponibilidad
    @Query("SELECT COUNT(c) > 0 FROM Cita c WHERE c.profesional = :profesional " +
            "AND c.estado NOT IN (azc.uam.app.model.enums.EstadoCita.CANCELADA, azc.uam.app.model.enums.EstadoCita.RECHAZADA) " +
            "AND ((c.fechaHoraInicio < :fin) AND (c.fechaHoraFin > :inicio))")
    boolean existsCitaSuperpuesta(
            @Param("profesional") Usuario profesional,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    // Citas de un profesional en un rango de fechas
    @Query("SELECT c FROM Cita c WHERE c.profesional = :profesional " +
            "AND c.fechaHoraInicio BETWEEN :inicio AND :fin " +
            "ORDER BY c.fechaHoraInicio")
    List<Cita> findCitasProfesionalEntreFechas(
            @Param("profesional") Usuario profesional,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    @Query("SELECT COUNT(c) > 0 FROM Cita c WHERE c.cliente = :cliente " +
            "AND c.estado NOT IN (azc.uam.app.model.enums.EstadoCita.CANCELADA, azc.uam.app.model.enums.EstadoCita.RECHAZADA) " +
            "AND ((c.fechaHoraInicio < :fin) AND (c.fechaHoraFin > :inicio))")
    boolean existsByClienteAndFechaHoraInicioBetween(
            @Param("cliente") Usuario cliente,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);

    boolean existsByClienteAndFechaHoraInicioBetweenAndEstadoNotIn(
            Usuario cliente,
            LocalDateTime fechaHoraInicio,
            LocalDateTime fechaHoraFin,
            List<EstadoCita> estados);

    List<Cita> findByFechaHoraInicioBetween(LocalDateTime inicio, LocalDateTime fin);
}