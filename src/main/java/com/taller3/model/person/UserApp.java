package com.taller3.model.person;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class UserApp {
	public interface addValidator {};
	
	public interface updateValidator {};
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotBlank(groups={addValidator.class, updateValidator.class}, message="Username cannot be blank")
	@Size(min=3, groups={addValidator.class, updateValidator.class}, message="Username must be 3 characters or longer")
	private String username;
	
	@NotBlank(groups=addValidator.class, message="Password cannot be blank")
	@Size(min=8, groups=addValidator.class, message="Password must be 8 characters or longer")
	private String password;
	
	@Transient
	private String repeatPassword;
	
	@NotNull(groups={addValidator.class, updateValidator.class}, message="Type cannot be null")
	private UserType type;
}
