package gov.santafe.establecimientosms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;



@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
public class EstablecimientoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final Date fechaInauguracion;
    private boolean isActive;
}
