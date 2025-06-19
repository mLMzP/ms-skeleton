package com.lm2a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm2a.dto.TokenDto;
import com.lm2a.dto.UserDto;
import com.lm2a.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody UserDto dto){
		UserDto userDto = service.saveUser(dto);
		return ResponseEntity.ok(userDto);		
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody UserDto dto){
		TokenDto tokenDto = service.login(dto);
		return ResponseEntity.ok(tokenDto);		
	}
	
	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate(@RequestParam("token") String token){
		TokenDto tokenDto = service.validate(token);
		return ResponseEntity.ok(tokenDto);			
	}

}
