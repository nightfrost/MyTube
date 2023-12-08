package io.nightfrost.mytubeapi.controllers;

import io.nightfrost.mytubeapi.dto.UserDTO;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "user")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PostMapping()
	public ResponseEntity<User> addUser (@RequestBody User newUser) {
		return ResponseEntity.ok(userService.addUser(newUser));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<User> updateUser (@RequestBody User updatedUser, @PathVariable long id) {
		return ResponseEntity.ok(userService.updateUser(id, updatedUser));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteUser (@PathVariable long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}
}
