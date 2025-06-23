package gov.santafe.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class GatewayConfig {
	
	private static final Logger log = LoggerFactory.getLogger(GatewayConfig.class);
	
	@Autowired
	private AuthFilter authFilter;
	
	@Bean
	@Profile("static-routing")
	RouteLocator staticConfigurationNoEureka(RouteLocatorBuilder builder) {
		log.info("Enrutado estatico");
		return builder.routes()
				.route(r -> r.path("/api/v1/establecimiento/*").uri("http://localhost:8001"))
				.route(r -> r.path("/api/v1/persona/*").uri("http://localhost:8002"))
				.build();
	}
	
	@Bean
	@Profile("dynamic-routing")
	RouteLocator dynamicConfigurationNEureka(RouteLocatorBuilder builder) {
		log.info("Enrutado dinamico");
		return builder.routes()
				.route(r -> r.path("/api/v1/establecimiento/**").uri("lb://establecimientos-ms"))
				.route(r -> r.path("/api/v1/persona/**").uri("lb://personas-ms"))
				.build();
	}


}
