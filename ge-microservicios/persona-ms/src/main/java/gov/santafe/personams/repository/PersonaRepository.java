package gov.santafe.personams.repository;

import gov.santafe.personams.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findDirectorByEstablecimientoId(long establecimientoId);
}
