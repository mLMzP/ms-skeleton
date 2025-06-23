package gov.santafe.authserver.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenDto {

	private final String token;

}
