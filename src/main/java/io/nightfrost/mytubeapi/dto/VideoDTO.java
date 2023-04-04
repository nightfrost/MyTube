package io.nightfrost.mytubeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VideoDTO {
	private long id;

	private String name;

	private long userId;
}
