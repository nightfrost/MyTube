package io.nightfrost.mytubeapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.nightfrost.mytubeapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User getByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT username FROM myuser")
	List<String> getAllUsernames();

	@Query(nativeQuery = true, value = "Select * FROM myuser")
	List<User> getAllUsers();

	@Query(nativeQuery = true, value = "SELECT `user`.`id`,\r\n"
			+ "    `user`.`created_at`,\r\n"
			+ "    `user`.`dob`,\r\n"
			+ "    `user`.`email`,\r\n"
			+ "    `user`.`enabled`,\r\n"
			+ "    `user`.`first_name`,\r\n"
			+ "    `user`.`last_name`,\r\n"
			+ "    `user`.`nationality`,\r\n"
			+ "    `user`.`password`,\r\n"
			+ "    `user`.`phone`,\r\n"
			+ "    `user`.`username`\r\n"
			+ "FROM `mytube`.`myuser`;")
	User getById(long id);
}
