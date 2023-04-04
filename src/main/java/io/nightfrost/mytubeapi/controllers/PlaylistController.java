package io.nightfrost.mytubeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.services.PlaylistService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "playlist")
@AllArgsConstructor
public class PlaylistController {
	@Autowired
	PlaylistService playlistService;

	@GetMapping()
	public ResponseEntity<List<Playlist>> getAllPlaylists() {
		return ResponseEntity.ok(playlistService.getAllPlaylists());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Playlist> getPlaylistById(@PathVariable long id) {
		return ResponseEntity.ok(playlistService.getPlaylistById(id));
	}

	@PostMapping()
	public ResponseEntity<Playlist> addPlaylist(@Validated @RequestBody Playlist playlist) {
		return ResponseEntity.ok(playlistService.addPlaylist(playlist));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Playlist> updatePlaylist(@Validated @RequestBody Playlist newPlaylist, @PathVariable long id) {
		return ResponseEntity.ok(playlistService.updatePlaylist(id, newPlaylist));
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deletePlaylist(@PathVariable long id) {
		return ResponseEntity.ok(playlistService.deletePlaylist(id));
	}
}
