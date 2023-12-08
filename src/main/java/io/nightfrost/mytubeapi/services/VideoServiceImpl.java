package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.dto.VideoDTO;
import io.nightfrost.mytubeapi.dto.VideoDataDTO;
import io.nightfrost.mytubeapi.exceptions.VideoAlreadyExistsException;
import io.nightfrost.mytubeapi.exceptions.VideoIOException;
import io.nightfrost.mytubeapi.exceptions.VideoNotFoundException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.models.Video;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {

    private VideoRepository videoRepository;

    private ModelMapper modelMapper;

    @Deprecated
    @Override
    public VideoDTO getVideo(String name) {
        Video returnVideo;
        VideoDTO returnVideoDto = new VideoDTO();

        try {
            if (!(returnVideo = videoRepository.findByName(name)).isEmpty()) {
                returnVideoDto = modelMapper.map(returnVideo, VideoDTO.class);
                return returnVideoDto;
            } else {
                throw new VideoNotFoundException();
            }
        } catch (Exception e) {
            System.out.println(
                    "Retrieval of video from database failed, returning empty video object. See stack trace...");
            System.out.println(e.getMessage());
            return returnVideoDto;
        }
    }

    @Override
    public String saveVideo(MultipartFile file, String name, User userId) {
        try {
            if (videoRepository.existsByName(name)) {
                throw new VideoAlreadyExistsException();
            } else {
                Video newVideo = new Video(name, file.getBytes(), userId);
                videoRepository.saveAndFlush(newVideo);
                return "Video saved";
            }
        } catch (IOException e) {
            System.out.println("Writing to disk failed, video was not saved.");
            System.out.println(e.getMessage());
            throw new VideoIOException();
        } catch (Exception e) {
            System.out.println("Video was not saved. Check stack trace...");
            System.out.println(e.getMessage());
            return "Video was not saved. Check stack trace...";
        }
    }

    @Override
    public List<String> getAllVideoNames() {
        return videoRepository.getAllEntryNames();
    }

    @Override
    public VideoDataDTO getVideoData(long videoId) {
        Video returnVideo;
        VideoDataDTO returnVideoDataDTO = new VideoDataDTO();

        try {
            returnVideo = videoRepository.getReferenceById(videoId);
            returnVideoDataDTO = modelMapper.map(returnVideo, VideoDataDTO.class);
            return returnVideoDataDTO;
        } catch (EntityNotFoundException e) {
            throw new VideoNotFoundException();
        } catch (Exception e) {
            System.out.println("Retrieval of Video(s) failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            return returnVideoDataDTO;
        }
    }

    @Override
    public VideoDTO getVideo(long videoId) {
        Video returnVideo;
        VideoDTO returnVideoDTO = new VideoDTO();

        try {
            returnVideo = videoRepository.getReferenceById(videoId);
            return modelMapper.map(returnVideo, VideoDTO.class);

        } catch (EntityNotFoundException e) {
            throw new VideoNotFoundException();
        }
        catch (Exception e) {
            System.out.println("Retrieval of Video(s) failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            return returnVideoDTO;
        }
    }
}
