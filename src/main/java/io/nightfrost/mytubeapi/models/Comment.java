package io.nightfrost.mytubeapi.models;

import java.time.OffsetDateTime;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String body;
	
	@Basic
	private int likes;
	
	@Basic
	private int dislikes;
	
	/*
	 * Many Comments to One Video.
	 * This is the owning side.
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    @JsonIgnoreProperties("comment")
	private Video video;
	
	/*
	 * Many Comments to One User
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("comment")
	private User user;
	
	@Basic
	private boolean isPinned;
	
	@Basic
	private OffsetDateTime createdAt;
	
	@Basic
	private OffsetDateTime updatedAt;
}
