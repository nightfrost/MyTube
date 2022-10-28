package io.nightfrost.mytubeapi.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	/*
	 * One Video has Many Comments
	 * NOT owning side.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "video", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("video")
	private Set<Comment> comments;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    @JsonIgnoreProperties("video")
	private Playlist playlist;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("video")
	private User user;

	public Video(String name, byte[] bytes) {
		this.name = name;
		this.data = bytes;
	}
	
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
}
