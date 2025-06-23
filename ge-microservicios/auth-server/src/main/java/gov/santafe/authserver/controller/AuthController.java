package gov.santafe.authserver.controller;


import gov.santafe.authserver.dto.TokenDto;
import gov.santafe.authserver.dto.UserDto;
import gov.santafe.authserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
