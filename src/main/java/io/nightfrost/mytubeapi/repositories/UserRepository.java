package io.nightfrost.mytubeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.nightfrost.mytubeapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT username FROM user")
	List<String> getAllUsernames();
	
	@Query(nativeQuery = true, value = "Select * FROM user")
	List<User> getAllUsers();
}
