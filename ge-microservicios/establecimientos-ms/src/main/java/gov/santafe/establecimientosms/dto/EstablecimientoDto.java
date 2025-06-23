package gov.santafe.establecimientosms.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class EstablecimientoDto {    private final long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Date fechaInauguracion;
    private boolean isActive;
}
