package io.nightfrost.mytubeapi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.nightfrost.mytubeapi.services.VideoService;
import io.nightfrost.mytubeapi.services.VideoServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(HelperController.BASE_ENDPOINT + "video")
@AllArgsConstructor
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@PostMapping
	public ResponseEntity<String> saveVideo(@RequestParam MultipartFile file, @RequestParam String name) throws IOException{
		videoService.saveVideo(file, name);
		return ResponseEntity.ok("Video has been successfully saved");
	}
	
	@GetMapping("{name}")
	public ResponseEntity<Resource> getVideoByName(@PathVariable String name) {
		return ResponseEntity.
				ok(new ByteArrayResource(videoService.getVideo(name).getData()));
	}
	
	@GetMapping("all")
	public ResponseEntity<List<String>> getAllVideoNames() {
		return ResponseEntity.ok(videoService.getAllVideoNames());
	}
}
