// src/main/java/com/example/demo/DataInitializer.java
package gov.santafe.personams;

import gov.santafe.personams.entity.Persona;
import gov.santafe.personams.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PersonaRepository personaRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing persons data...");

//        private final String nombres;
//        private final String apellidos;
//        private final int cargoId;
//        private final long establecimientoId;
//        private final String nivel;

        // Create instances of your model object (Persona)
        Persona persona1 = new Persona("Juan", "Perez", 3, 1L, "primaria");
        Persona persona2 = new Persona("Benjamin", "Garcia", 3, 1L, "preescolar");
        Persona persona3 = new Persona("Jose", "Perello", 3, 1L, "secundaria");

        // Save them to the database using the repository
        personaRepository.save(persona1);
        personaRepository.save(persona2);
        personaRepository.save(persona3);

        System.out.println("Persons saved:");
        personaRepository.findAll().forEach(System.out::println);
    }
}