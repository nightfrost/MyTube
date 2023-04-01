package io.nightfrost.mytubeapi.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nightfrost.mytubeapi.dto.UserDTO;
import io.nightfrost.mytubeapi.exceptions.UserAlreadyExistsException;
import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> usersList = new ArrayList<>();
		List<UserDTO> usersListDto = new ArrayList<>();
		
		try {
			if (!(usersList = userRepository.getAllUsers()).isEmpty()) {
				usersList.forEach((user) -> usersListDto.add(modelMapper.map(user, UserDTO.class)));
				return usersListDto;
			} else {
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of user(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return usersListDto;
		}
	}

	@Override
	public UserDTO getUserById(long id) {
		User returnUser = null;
		UserDTO returnUserDto = null;
		
		try {
			if ((returnUser = userRepository.getReferenceById(id)).getEmail() != null) {
				returnUserDto = modelMapper.map(returnUser, UserDTO.class);
				return returnUserDto;
			} else {
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of user(s) failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			throw new UserNotFoundException();
		}
	}

	@Override
	public User addUser(User newUser) {
		User returnUser = null;
		try {
			if (userRepository.existsByEmail(newUser.getEmail()) || userRepository.existsByUsername(newUser.getUsername()) ) {
				throw new UserAlreadyExistsException();
			} else {
				newUser.calcAge();
				returnUser = userRepository.saveAndFlush(newUser);
				return returnUser;
			}
		} catch (Exception e) {
			System.out.println("Saving user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnUser;
		}
	}

	@Override
	public User updateUser(Long id, User newUser) {
		User returnUser = null;
		try {
			if ((returnUser = userRepository.getReferenceById(id)) != null) {
				returnUser = (User) HelperService.partialUpdate(returnUser, newUser);
				returnUser.setId(id);
				userRepository.saveAndFlush(returnUser);
				return returnUser;
			} else {
				throw new UserNotFoundException();
			}
		} catch (BeansException e) {
			System.out.printf("Failed to copy values into user object... Returning empty object\nPrinting message...");
			System.out.println(e.getMessage());
			return returnUser = null;
		} catch (Exception e) {
			System.out.println("Saving user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return returnUser = null;
		}
	}

	@Override
	public String deleteUser(Long id) {
		try {
			if (userRepository.existsById(id)) {
				userRepository.deleteById(id);
				return userRepository.existsById(id) ? "User not deleted..." : "User with ID: " + id.toString() + " deleted.";
			} else {				
				throw new UserNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Deleting user failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return userRepository.existsById(id) ? "User not deleted..." : "User with ID: " + id.toString() + " deleted.";
		}
	}

}
