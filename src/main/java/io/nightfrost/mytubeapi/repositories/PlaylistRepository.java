package io.nightfrost.mytubeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nightfrost.mytubeapi.models.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
