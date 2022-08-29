package io.nightfrost.mytubeapi.models;

import java.time.OffsetDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
	@ManyToOne
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;
	
	/*
	 * Many Comments to One User
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@Basic
	private boolean isPinned;
	
	@Basic
	private OffsetDateTime createdAt;
	
	@Basic
	private OffsetDateTime updatedAt;
}
