package io.nightfrost.mytubeapi.services;

import java.util.List;

import io.nightfrost.mytubeapi.models.User;

public interface UserService {
	List<User> getAllUsers();
	
	User getUserById(long id);
	
	User addUser(User newUser);
	
	String updateUser(Long id, User newUser);
	
	String deleteUser(Long id);
}