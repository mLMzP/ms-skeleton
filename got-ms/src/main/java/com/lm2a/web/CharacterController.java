package com.lm2a.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/got/")
public class CharacterController {


	private Faker faker = new Faker();
	private List<String> charactersGOT = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0; i<10; ++i) {
			charactersGOT.add(faker.gameOfThrones().character());
		}
	}
	
	@GetMapping
	public List<String> getAllCharacters(){
		return charactersGOT;
	}
	
	@PostMapping("/got")
	public String addCharacter(@RequestBody String character) {
		charactersGOT.add(character);
		return character;
	}
	
	@GetMapping("/got/{name}")
	public String getCharacterByName(@PathVariable("name") String name) {
		return charactersGOT.stream().filter(c -> c.equals(name)).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s no existe", name)));
	}
	
//	@GetMapping("/dragonBall/search")
//	public List<String> getCharactersByPrefix(@RequestParam("prefix") String prefix){
//		List<String> result = charactersDB.stream().filter(x ->x.startsWith(prefix) ).collect(Collectors.toList());
//		if(result.isEmpty()) {
//			throw new CharacterNotFound();
//		}
//		return result;
//	}
	
	@GetMapping("/got/search")
	public ResponseEntity<List<String>> getCharactersByPrefix(@RequestParam("prefix") String prefix){
		List<String> result = charactersGOT.stream().filter(x ->x.startsWith(prefix) ).collect(Collectors.toList());
		if(result.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
