package gov.santafe.authserver.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Entity
public class UserEntity implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;


}
