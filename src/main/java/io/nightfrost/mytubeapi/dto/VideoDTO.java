package io.nightfrost.mytubeapi.dto;

import java.util.Set;

import io.nightfrost.mytubeapi.models.Comment;
import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VideoDTO {
	private long id;
	
	private String name;
	
	private long userId;
}
