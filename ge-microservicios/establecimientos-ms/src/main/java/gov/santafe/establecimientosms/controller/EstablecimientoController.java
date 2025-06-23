package gov.santafe.establecimientosms.controller;


import gov.santafe.establecimientosms.dto.EstablecimientoDto;
import gov.santafe.establecimientosms.entity.EstablecimientoEntity;
import gov.santafe.establecimientosms.model.Persona;
import gov.santafe.establecimientosms.service.EstablecimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/establecimiento")
@RequiredArgsConstructor
public class EstablecimientoController {

    final EstablecimientoService establecimientoService;

    @PostMapping
    public EstablecimientoEntity saveEstablecimiento(@RequestBody EstablecimientoDto establecimientoDto){
        return establecimientoService.create(establecimientoDto);
    }

    @GetMapping("/{establecimientoId}/director")
    public List<Persona> findAllDirectorsByEstablecimiento(@PathVariable("establecimientoId") int establecimientoId){
        return establecimientoService.findAllDirectorsByEstablecimiento(establecimientoId);
    }

    @GetMapping
    public List<EstablecimientoEntity> findAllEstablecimientos(){
        return establecimientoService.findAllEstablecimientos();
    }
}
