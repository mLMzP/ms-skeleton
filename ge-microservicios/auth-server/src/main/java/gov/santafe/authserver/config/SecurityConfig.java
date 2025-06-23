package gov.santafe.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request.anyRequest().permitAll());
		http.headers(headers ->
				headers
						.contentTypeOptions(withDefaults())
						.xssProtection(withDefaults())
						.cacheControl(withDefaults())
						.httpStrictTransportSecurity(withDefaults())
						.frameOptions(withDefaults()
						));//agregado para evitar problemas de frames
		return http.build();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
