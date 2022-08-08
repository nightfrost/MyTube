package io.nightfrost.mytubeapi.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.*;

/**
 * <P>{@code User} is a model used to define the User Table as well as the object itself.
 */
@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 50, nullable = false)
	private String firstName;
	
	@Column(length = 50, nullable = false)
	private String lastName;
	
	@Column(length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(length = 255, nullable = false)
	private String password;
	
	@Column(length = 150, nullable = false, unique = true)
	private String email;
	
	@Column(length = 50, nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private Date dob;
	
	@Column(length = 100, nullable = false)
	private String nationality;
	
	@Transient
	private int age;
	
	@Column(nullable = false)
	private Timestamp createdAt;
	
	@Column(nullable = false)
	private boolean enabled;
	
	/**
	 * <p> Method used to calculate a users age using the DOB field.
	 * 
	 * @return Users age in the form of an Integer
	 */
	public int calcAge() {
		Date today = new Date(System.currentTimeMillis());
		if ((this.dob != null) && (today != null)) {
			return Period.between(this.dob.toLocalDate(), today.toLocalDate()).getYears();
		} else {
			return -1;
		}
	}
}
