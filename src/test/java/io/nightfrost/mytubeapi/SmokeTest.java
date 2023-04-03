package io.nightfrost.mytubeapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.nightfrost.mytubeapi.services.VideoService;

@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private VideoService videoService;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(videoService).isNotNull();
	}

}
