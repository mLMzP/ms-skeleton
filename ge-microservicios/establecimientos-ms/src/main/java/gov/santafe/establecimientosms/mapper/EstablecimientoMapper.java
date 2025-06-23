package gov.santafe.establecimientosms.mapper;


import gov.santafe.establecimientosms.dto.EstablecimientoDto;
import gov.santafe.establecimientosms.entity.EstablecimientoEntity;
import lombok.Builder;

import java.util.Optional;

@Builder
public class EstablecimientoMapper {

    public static EstablecimientoDto getDtoFromEntity(Optional<EstablecimientoEntity> optEstablecimientoEntity) {
        if(optEstablecimientoEntity.isPresent()){
            EstablecimientoEntity establecimientoEntity = optEstablecimientoEntity.get();
            return EstablecimientoDto.builder()
                    .id(establecimientoEntity.getId())
                    .direccion(establecimientoEntity.getDireccion())
                    .nombre(establecimientoEntity.getNombre())
                    .telefono(establecimientoEntity.getTelefono())
                    .build();
        }
        return null;
    }


    public static EstablecimientoDto createDto(long id, String nombre, String direccion, String telefono) {
        return EstablecimientoDto.builder()
                .id(id)
                .direccion(direccion)
                .nombre(nombre)
                .telefono(telefono)
                .build();
    }
}
