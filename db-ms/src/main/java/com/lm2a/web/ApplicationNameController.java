package com.lm2a.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lm2a.config.DBConfig;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {

	@Autowired
	private DBConfig config;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);
	
	@GetMapping
	public ResponseEntity<String> getAppName(){
		log.info("Se ha invocado este metodo en forma remota");
		return ResponseEntity.ok(config.getApplicationName());
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testConditionalFunctionality(){
		if(config.isFunctionActive()) {
			log.info("Cuidado funcionalidad secreta ACTIVADA");
			return ResponseEntity.ok("Cuidado funcionalidad secreta ACTIVADA");
		}else {
			log.info("Cuidado funcionalidad secreta DESACTIVADA");
			return ResponseEntity.ok("Cuidado funcionalidad secreta DESACTIVADA");
		}
	}
}
