package io.nightfrost.mytubeapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nightfrost.mytubeapi.exceptions.PlaylistNotFoundException;
import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{
	
	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Playlist> getAllPlaylists() {
		List<Playlist> returnList = new ArrayList<>();
		
		try {
			if(!(returnList = playlistRepository.findAll()).isEmpty()) {
				return returnList;
			} else {
				throw new PlaylistNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of playlist(s) failed. Returning empty object.");
			System.out.println(e.getMessage());
			return returnList;
		}
	}

	@Override
	public Playlist getPlaylistById(long id) {
		Playlist returnPlaylist = new Playlist();
		
		try {
			if ((returnPlaylist = playlistRepository.getReferenceById(id)) != null) {
				return returnPlaylist;
			} else {
				throw new PlaylistNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Retrieval of Playlist with ID: " + id + " couldn't be retrieved.");
			System.out.println(e.getMessage());
			return returnPlaylist;
		}
	}

	@Override
	public Playlist addPlaylist(Playlist playlist) {
		Playlist returnPlaylist = new Playlist();

		//for simplying creating a playlist, nothing needs to be checked.
		//You are fully able to have two identical lists, 
		//(except id, but hibernate handles that.)
		try {
			return playlistRepository.saveAndFlush(playlist);
		} catch (Exception e) {
			System.out.println("Failed to save playlist. See stack trace");
			System.out.println(e.getMessage());
			return returnPlaylist;
		}
	}

	@Override
	public Playlist updatePlaylist(long id, Playlist playlist) {
		Playlist returnPlaylist = new Playlist();
		
		try {
			if ((returnPlaylist = playlistRepository.getReferenceById(id)) == null) {
				throw new PlaylistNotFoundException();
			} else {
				returnPlaylist = (Playlist)HelperService.partialUpdate(returnPlaylist, playlist);
				playlistRepository.saveAndFlush(returnPlaylist);
				return returnPlaylist;
			}
		} catch (Exception e) {
			System.out.println("Update of Playlist with ID: " + playlist.getId() + " failed");
			System.out.println(e.getMessage());
			return returnPlaylist;
		}
	}

	@Override
	public String deletePlaylist(long id) {
		try {
			if (playlistRepository.existsById(id)) {
				playlistRepository.deleteById(id);
				return playlistRepository.existsById(id) ? "Playlist not deleted." : "Playlist with ID: " + id + " deleted.";
			} else {
				throw new PlaylistNotFoundException();
			}
		} catch (Exception e) {
			System.out.println("Deleting playlist failed. Returning empty object. See stack trace.");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
