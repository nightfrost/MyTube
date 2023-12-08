package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.models.Playlist;

import java.util.List;

public interface PlaylistService {

	List<Playlist> getAllPlaylists();

	Playlist getPlaylistById(long id);

	Playlist addPlaylist(Playlist playlist);

	Playlist updatePlaylist(long id, Playlist playlist);

	String deletePlaylist(long id);

}
