package io.nightfrost.mytubeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "user")
@AllArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
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
