// src/main/java/com/example/demo/DataInitializer.java
package gov.santafe.establecimientosms;

import gov.santafe.establecimientosms.dto.EstablecimientoDto;
import gov.santafe.establecimientosms.entity.EstablecimientoEntity;
import gov.santafe.establecimientosms.repository.EstablecimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EstablecimientoRepository establecimientoRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing persons data...");

//        private final String nombre;
//        private final String direccion;
//        private final String telefono;

        // Create instances of your model object (Persona)
        EstablecimientoDto dto = EstablecimientoDto.builder()
                .nombre("Instituto Politecnico Rosario")
                .direccion("Pellegrini 150")
                .telefono("4288757")
                .fechaInauguracion(new Date())
                .isActive(false)
                .build();


        EstablecimientoEntity establecimientoEntity = new EstablecimientoEntity(dto.getNombre(), dto.getDireccion(), dto.getTelefono(), dto.getFechaInauguracion());
        establecimientoEntity.setActive(dto.isActive());
        // Save them to the database using the repository
        establecimientoRepository.save(establecimientoEntity);


        System.out.println("Establecimiento saved:");
        establecimientoRepository.findAll().forEach(System.out::println);
    }
}