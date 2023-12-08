package io.nightfrost.mytubeapi.controllers;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.services.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "video")
@AllArgsConstructor
@CrossOrigin
public class VideoController {

	private VideoService videoService;

	@PostMapping
	public ResponseEntity<String> saveVideo(@RequestParam MultipartFile file, @RequestParam String name, @RequestParam User userId) {
		return ResponseEntity.ok(videoService.saveVideo(file, name, userId));
	}

	@GetMapping("all")
	public ResponseEntity<List<String>> getAllVideoNames() {
		return ResponseEntity.ok(videoService.getAllVideoNames());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<VideoDTO> getVideoById(@PathVariable long id) {
		return ResponseEntity.ok(videoService.getVideo(id));
	}

	@GetMapping(value = "/data/{id}")
	public ResponseEntity<Resource> getVideoDataById(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK)
	               .contentType(MediaType.APPLICATION_OCTET_STREAM)
	               .body(new ByteArrayResource(videoService.getVideoData(id).getData()));
	}
}
