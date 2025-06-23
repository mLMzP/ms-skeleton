package gov.santafe.authserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	ModelMapper getMapper() {
		return new ModelMapper();
	}
}
