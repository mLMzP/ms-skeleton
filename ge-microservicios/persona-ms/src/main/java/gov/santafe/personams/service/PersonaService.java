package gov.santafe.personams.service;


import gov.santafe.personams.entity.Persona;
import gov.santafe.personams.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaService {

    final PersonaRepository personaRepository;

    public List<Persona> findDirectoresByEstablecimientoId(long establecimientoId) {
        return personaRepository.findDirectorByEstablecimientoId(establecimientoId);
    }

}
