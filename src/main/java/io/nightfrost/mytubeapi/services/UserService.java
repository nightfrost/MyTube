package io.nightfrost.mytubeapi.services;

import java.util.List;

import io.nightfrost.mytubeapi.models.User;

public interface UserService {
	List<User> getAllUsers();
	
	User getUserById(long id);
	
	User addUser(User newUser);
	
	User updateUser(Long id, User newUser);
	
	void deleteUser(Long id);
}