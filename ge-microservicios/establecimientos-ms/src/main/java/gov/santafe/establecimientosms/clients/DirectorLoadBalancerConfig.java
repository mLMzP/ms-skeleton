package gov.santafe.establecimientosms.clients;


import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DirectorLoadBalancerConfig {

    @Bean
    ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext ctx){
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient()
                //.withDiscoveryClient()
                //.withHealthChecks()
                .build(ctx);

    }
}
