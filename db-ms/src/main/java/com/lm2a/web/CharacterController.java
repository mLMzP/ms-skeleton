package com.lm2a.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.lm2a.service.FooService;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/db/")
public class CharacterController {
	
	@Autowired
	FooService fooService;
	
	private static final Logger log = LoggerFactory.getLogger(CharacterController.class);
	
	private Faker faker = new Faker();
	private List<String> charactersDB = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0; i<10; ++i) {
			charactersDB.add(faker.dragonBall().character());
		}
	}
	
	@GetMapping
	public List<String> getAllCharacters(){
		log.info("Se ha invocado este metodo en forma remota");
		fooService.miLog();
		return charactersDB;
	}
	
//	@GetMapping("/test")
//	public String getError(){
//		try {
//			int x = 1/0;
//		} catch (Exception e) {
//			log.info("Fallo: "+e.getMessage());
//			e.printStackTrace();
//		}
//		fooService.miLog();
//		return "FALLO";
//	}
	
	
	
	
	@PostMapping("/dragonBall")
	public String addCharacter(@RequestBody String character) {
		charactersDB.add(character);
		return character;
	}
	
	@GetMapping("/dragonBall/{name}")
	public String getCharacterByName(@PathVariable("name") String name) {
		return charactersDB.stream().filter(c -> c.equals(name)).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s no existe", name)));
	}
	
//	@GetMapping("/dragonBall/search")
//	public List<String> getCharactersByPrefix(@RequestParam("prefix") String prefix){
//		List<String> result = charactersDB.stream().filter(x ->x.startsWith(prefix) ).collect(Collectors.toList());
//		if(result.isEmpty()) {
//			throw new CharacterNotFound();
//		}
//		return result;
//	}
	
	@GetMapping("/dragonBall/search")
	public ResponseEntity<List<String>> getCharactersByPrefix(@RequestParam("prefix") String prefix){
		List<String> result = charactersDB.stream().filter(x ->x.startsWith(prefix) ).collect(Collectors.toList());
		if(result.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
