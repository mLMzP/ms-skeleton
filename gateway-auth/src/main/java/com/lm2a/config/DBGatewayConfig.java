package com.lm2a.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DBGatewayConfig {
	
	private static final Logger log = LoggerFactory.getLogger(DBGatewayConfig.class);
	
	@Autowired
	private AuthFilter authFilter;
	
	@Bean
	@Profile("static-routing")
	RouteLocator staticConfigurationNoEureka(RouteLocatorBuilder builder) {
		log.info("Enrutado estatico");
		return builder.routes()
				.route(r -> r.path("/api/v1/db/*").uri("http://localhost:8002"))
				.route(r -> r.path("/api/v1/got/*").uri("http://localhost:8011"))
				.build();
	}
	
	@Bean
	@Profile("dynamic-routing")
	RouteLocator dynamicConfigurationNEureka(RouteLocatorBuilder builder) {
		log.info("Enrutado dinamico");
		return builder.routes()
				.route(r -> r.path("/api/v1/db/*").uri("lb://db"))
				.route(r -> r.path("/api/v1/got/*").uri("lb://got"))
				.build();
	}
	
	@Bean
	@Profile("circuit-breaking-routing")
	RouteLocator dynamicConfigurationEurekaCB(RouteLocatorBuilder builder) {
		log.info("Enrutado dinamico con Circuit Breaking");
		return builder.routes()
				.route(r -> r.path("/api/v1/db/*")
						.filters(f -> f.filter(authFilter))
						.uri("lb://db"))
				
				
				.route(r -> r.path("/api/v1/got/*")
						.filters(f -> f.circuitBreaker(c -> c.setName("failoverCB")
								.setFallbackUri("forward:/api/v1/got-failover/*")
								.setRouteId("gotFailover")))
						.uri("lb://got"))
				.route(r -> r.path("/api/v1/got-failover/").uri("lb://got-failover"))
				.route(r -> r.path("/api/v1/consumer/*").uri("lb://consumer"))
				.route(r -> r.path("/auth/**").uri("lb://auth-server"))
				.build();
	}

}
