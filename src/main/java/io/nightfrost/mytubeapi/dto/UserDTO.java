package io.nightfrost.mytubeapi.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
	
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String email;
	
	private String phone;
	
	private String nationality;
	
	private Timestamp createdAt;
	
	private boolean enabled;

}
