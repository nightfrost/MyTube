package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.exceptions.InternalErrorException;
import io.nightfrost.mytubeapi.exceptions.PlaylistNotFoundException;
import io.nightfrost.mytubeapi.models.Playlist;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    PlaylistRepository playlistRepository;

    VideoRepository videoRepository;

    UserRepository userRepository;

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> returnList = new ArrayList<>();

        try {
            if (!(returnList = playlistRepository.findAll()).isEmpty()) {
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
        try {
            return playlistRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new PlaylistNotFoundException();
        } catch (Exception e) {
            System.out.println("Retrieval of Playlist with ID: " + id + " couldn't be retrieved.");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
        }
    }

    @Override
    public Playlist addPlaylist(Playlist playlist) {
        Playlist returnPlaylist = new Playlist();

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
        Playlist returnPlaylist;

        try {
            returnPlaylist = playlistRepository.getReferenceById(id);
            HelperService.partialUpdate(returnPlaylist, playlist);
            returnPlaylist.setId(id);
            return playlistRepository.saveAndFlush(returnPlaylist);
        } catch (EntityNotFoundException e) {
            throw new PlaylistNotFoundException();
        } catch (Exception e) {
            System.out.println("Update of Playlist with ID: " + playlist.getId() + " failed");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
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
            throw new InternalErrorException();
        }
    }

}
