package gov.santafe.authserver.dto;


import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserDto {

	private int id;
	private String username;
	private String password;
	

	
}
