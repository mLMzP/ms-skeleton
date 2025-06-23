package gov.santafe.establecimientosms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstablecimientosMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstablecimientosMsApplication.class, args);
    }

}
