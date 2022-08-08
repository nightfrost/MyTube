package io.nightfrost.mytubeapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.repositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> usersList = new ArrayList<>();
		try {
			if (!(usersList = userRepository.getAllUsers()).isEmpty()) {
				return usersList;
			} else {
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of users failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return usersList;
		}
	}

	@Override
	public User getUserById(long id) {
		User returnUser = null;
		try {
			if ((returnUser = userRepository.getReferenceById(id)) != null) {
				return returnUser;
			} else {
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of user(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnUser;
		}
	}

	@Override
	public User addUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Long id, User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
