package gov.santafe.establecimientosms.clients;



import gov.santafe.establecimientosms.model.Persona;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value="personas-ms") //, url = "${spring.cloud.openfeign.clients.serviceName.url}")
@LoadBalancerClient(name="personas-ms", configuration=DirectorLoadBalancerConfig.class)
public interface DirectorClient {

    @GetMapping("/api/v1/persona/establecimiento/{id}")
    public List<Persona> getEstablecimientoDirector(@PathVariable("id") long establecimientoId);
}
