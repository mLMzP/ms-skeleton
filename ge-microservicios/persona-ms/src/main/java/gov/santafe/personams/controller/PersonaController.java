package gov.santafe.personams.controller;


import gov.santafe.personams.entity.Persona;
import gov.santafe.personams.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
@RequiredArgsConstructor
public class PersonaController {

    final PersonaService personaService;

    @GetMapping("/establecimiento/{id}")
    public List<Persona> getEstablecimientoDirector(@PathVariable("id") long establecimientoId){
        List<Persona> directores = personaService.findDirectoresByEstablecimientoId(establecimientoId);
        return directores;
    }

}
