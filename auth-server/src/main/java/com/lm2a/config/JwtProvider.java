package com.lm2a.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.lm2a.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@PostConstruct
	public void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	public String createToken(UserEntity user) {
		Map<String, Object> claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("id", user.getId());
		claims.put("company-name", "lm2a");

		Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
		Instant expiration = issuedAt.plus(3, ChronoUnit.DAYS);
		return Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims).setIssuedAt(Date.from(issuedAt))
				.setExpiration(Date.from(expiration)).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.error("Token expirado: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("Token no sportado: " + e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Token malformado: " + e.getMessage());
		} catch (SignatureException e) {
			log.error("Token mal firmado: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("Token ilegal: " + e.getMessage());
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		try {
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalido");
		}
	}

}
