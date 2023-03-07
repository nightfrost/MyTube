package io.nightfrost.mytubeapi.models;

import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "video", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
	private Playlist playlist;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public Video(String name, byte[] bytes, User userId) {
		this.name = name;
		this.data = bytes;
		this.user = userId;
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
