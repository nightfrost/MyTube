package io.nightfrost.mytubeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.nightfrost.mytubeapi.models.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	Video findByName(String name);

	boolean existsByName(String name);

	@Query(nativeQuery = true, value = "SELECT name\r\n"
			+ "FROM `mytubeJava`.`video`;\r\n"
			+ "")
	List<String> getAllEntryNames();
}
