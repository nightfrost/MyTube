package io.nightfrost.mytubeapi.controllers;

import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.services.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "playlist")
@AllArgsConstructor
public class PlaylistController {

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
