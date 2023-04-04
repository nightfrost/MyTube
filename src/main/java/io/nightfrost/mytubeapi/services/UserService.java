package io.nightfrost.mytubeapi.services;

import java.util.List;

import io.nightfrost.mytubeapi.dto.UserDTO;
import io.nightfrost.mytubeapi.models.User;

public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO getUserById(long id);

	User addUser(User newUser);

	User updateUser(Long id, User newUser);

	String deleteUser(Long id);
}