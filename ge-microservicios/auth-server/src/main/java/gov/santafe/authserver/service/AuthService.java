package gov.santafe.authserver.service;


import gov.santafe.authserver.config.JwtProvider;
import gov.santafe.authserver.dto.TokenDto;
import gov.santafe.authserver.dto.UserDto;
import gov.santafe.authserver.entity.UserEntity;
import gov.santafe.authserver.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private JwtProvider provider;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ModelMapper mapper;

	public UserDto saveUser(UserDto dto) {
		Optional<UserEntity> result = repository.findByUsername(dto.getUsername());
		if (result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("El usuario %s ya existe", dto.getUsername()));
		}

		UserEntity entity = repository.save(UserEntity.builder().username(dto.getUsername()).password(encoder.encode(dto.getPassword())).build());

		return mapper.map(entity, UserDto.class);
	}

	public TokenDto login(UserDto user) {
		Optional<UserEntity> result = repository.findByUsername(user.getUsername());
		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					String.format("El usuario %s no existe", user.getUsername()));
		}
		if (encoder.matches(user.getPassword(), result.get().getPassword())) {
			return new TokenDto(provider.createToken(result.get()));
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	}

	public TokenDto validate(String token) {
		if (!provider.validate(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		String username = provider.getUsernameFromToken(token);
		Optional<UserEntity> result = repository.findByUsername(username);
		if (!result.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		return new TokenDto(token);
	}
}
