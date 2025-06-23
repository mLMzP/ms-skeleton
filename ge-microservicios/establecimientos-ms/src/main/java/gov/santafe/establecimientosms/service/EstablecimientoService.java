package gov.santafe.establecimientosms.service;


import gov.santafe.establecimientosms.clients.DirectorClient;
import gov.santafe.establecimientosms.dto.EstablecimientoDto;
import gov.santafe.establecimientosms.entity.EstablecimientoEntity;
import gov.santafe.establecimientosms.mapper.EstablecimientoMapper;
import gov.santafe.establecimientosms.model.Persona;
import gov.santafe.establecimientosms.repository.EstablecimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstablecimientoService {

    final EstablecimientoRepository establecimientoRepository;

    final DirectorClient directorClient;

    public Optional<EstablecimientoDto> findById(long id) {
        return Optional.ofNullable(EstablecimientoMapper.getDtoFromEntity(establecimientoRepository.findById(id)));
    }

    public EstablecimientoEntity create(EstablecimientoDto dto) {
        EstablecimientoEntity establecimientoEntity = new EstablecimientoEntity(dto.getNombre(), dto.getDireccion(), dto.getTelefono(), new Date());
        establecimientoEntity.setActive(dto.isActive());
        return establecimientoRepository.save(establecimientoEntity);
    }

    public List<EstablecimientoEntity>  findAllEstablecimientos() {
        return establecimientoRepository.findAll();
    }


    public List<Persona>  findAllDirectorsByEstablecimiento(long establecimientoId) {
        return directorClient.getEstablecimientoDirector(establecimientoId);
    }
}
