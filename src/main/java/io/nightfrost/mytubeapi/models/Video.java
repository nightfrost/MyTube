package io.nightfrost.mytubeapi.models;

import java.lang.reflect.Field;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String name;

	@Lob
	private byte[] data;

	/**
	 * @return Returns true or false, depending on if ALL fields hold data.
	 */
	public boolean isEmpty() {
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if (field.get(this) != null) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("Exception occured in validating fields..");
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

	public Video(String name, byte[] bytes) {
		this.name = name;
		this.data = bytes;
	}
}
