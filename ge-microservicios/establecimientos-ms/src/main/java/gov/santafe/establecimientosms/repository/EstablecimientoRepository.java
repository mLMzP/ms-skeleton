package gov.santafe.establecimientosms.repository;

import gov.santafe.establecimientosms.entity.EstablecimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablecimientoRepository extends JpaRepository<EstablecimientoEntity, Long> {
}
