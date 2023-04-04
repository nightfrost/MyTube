package io.nightfrost.mytubeapi.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Period;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>{@code User} is a model used to define the User Table as well as the object itself.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "myuser")
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

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String password;

	@Column(length = 150, nullable = false, unique = true)
	private String email;

	@Column(length = 50, nullable = false)
	private String phone;

	@Column(nullable = false)
	private Date dob;

	@Column(length = 25, nullable = false)
	private String nationality;

	@Transient
	private int age;

	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-mm-dd hh:mm:ss", timezone="Europe/Zagreb")
	private Timestamp createdAt;

	@Column(nullable = false)
	private boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Comment> comments;

//	@JsonGetter("comments")
//	public Set<String> commentsGetter() {
//		if (comments != null) {
//			return comments.stream().map(comments -> HelperController.BASE_ENDPOINT + "comment/" + comments.getVideo()).collect(Collectors.toSet());
//		} else {
//			return null;
//		}
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Video> videos;

//	@JsonGetter("videos")
//	public Set<String> videosGetter() {
//		if (videos != null) {
//			return videos.stream().map(videos -> HelperController.BASE_ENDPOINT + "video/" + videos.getId()).collect(Collectors.toSet());
//		} else {
//			return null;
//		}
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Playlist> playlists;

//	@JsonGetter("playlists")
//	public Set<String> playlistsGetter() {
//		if (playlists != null) {
//			return playlists.stream().map(playlists -> HelperController.BASE_ENDPOINT + "playlist/" + playlists.getId()).collect(Collectors.toSet());
//		} else {
//			return null;
//		}
//	}

	public User(long id, String firstName, String lastName, String username, String password, String email,
			String phone, Date dob, String nationality, int age, Timestamp createdAt, boolean enabled) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.nationality = nationality;
		this.age = age;
		this.createdAt = createdAt;
		this.enabled = enabled;
	}

	/**
	 * <p> Method used to calculate a users age using the DOB field.
	 *
	 *
	 */
	public void calcAge() {
		Date today = new Date(System.currentTimeMillis());
		if ((this.dob != null) && (today != null)) {
			this.age = Period.between(this.dob.toLocalDate(), today.toLocalDate()).getYears();
		} else {
			this.age = -1;
		}
	}
}
