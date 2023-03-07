package io.nightfrost.mytubeapi.models;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Basic
	@Column(length = 75, nullable = false)
	private String playlistName;
	
	/*
	 * @Basicc
	 * 
	 * @ElementCollection(targetClass = Long.class)
	 * 
	 * @CollectionTable(name = "video", joinColumns
	 * = @JoinColumn(referencedColumnName = "id")) private Set<Video> videos;
	 */
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("video")
	private Set<Video> videos;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("playlist")
	private User user;
}
