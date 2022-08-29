package io.nightfrost.mytubeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.nightfrost.mytubeapi.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query(value = "SELECT *"
			+ "FROM comment"
			+ "WHERE video_id = ?1")
	List<Comment> getCommentsByVideoId(long videoId);
}
