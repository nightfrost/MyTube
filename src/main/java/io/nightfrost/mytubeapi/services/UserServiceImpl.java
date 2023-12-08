package io.nightfrost.mytubeapi.services;

import io.nightfrost.mytubeapi.dto.UserDTO;
import io.nightfrost.mytubeapi.exceptions.HelperBeanParserException;
import io.nightfrost.mytubeapi.exceptions.InternalErrorException;
import io.nightfrost.mytubeapi.exceptions.UserAlreadyExistsException;
import io.nightfrost.mytubeapi.exceptions.UserNotFoundException;
import io.nightfrost.mytubeapi.models.User;
import io.nightfrost.mytubeapi.repositories.CommentRepository;
import io.nightfrost.mytubeapi.repositories.PlaylistRepository;
import io.nightfrost.mytubeapi.repositories.UserRepository;
import io.nightfrost.mytubeapi.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    VideoRepository videoRepository;

    CommentRepository commentRepository;

    PlaylistRepository playlistRepository;

    ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> usersList;
        List<UserDTO> usersListDto = new ArrayList<>();

        try {
            if (!(usersList = userRepository.getAllUsers()).isEmpty()) {
                usersList.forEach((user) -> usersListDto.add(modelMapper.map(user, UserDTO.class)));
                return usersListDto;
            } else {
                throw new UserNotFoundException();
            }
        } catch (Exception e) {
            System.out.println("Retrieval of user(s) failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
        }
    }

    @Override
    public UserDTO getUserById(long id) {
        User returnUser;
        UserDTO returnUserDto;

        try {
            if ((returnUser = userRepository.getReferenceById(id)).getEmail() != null) {
                returnUserDto = modelMapper.map(returnUser, UserDTO.class);
                return returnUserDto;
            } else {
                throw new UserNotFoundException();
            }
        } catch (Exception e) {
            System.out.println("Retrieval of user(s) failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
        }
    }

    @Override
    public User addUser(User newUser) {
        User returnUser;
        try {
            if (userRepository.existsByEmail(newUser.getEmail()) || userRepository.existsByUsername(newUser.getUsername())) {
                throw new UserAlreadyExistsException();
            } else {
                newUser.calcAge();
                returnUser = userRepository.saveAndFlush(newUser);
                return returnUser;
            }
        } catch (Exception e) {
            System.out.println("Saving user failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
        }
    }

    @Override
    public User updateUser(Long id, User newUser) {
        User returnUser;

        try {
            returnUser = userRepository.getReferenceById(id);
            HelperService.partialUpdate(returnUser, newUser);
            returnUser.setId(id); //Re-assign ID as partialUpdate sees null in newUser ID.
            userRepository.saveAndFlush(returnUser);
            return returnUser;
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        } catch (BeansException e) {
            System.out.println("Failed to copy values into user object... Returning empty object\nPrinting message...");
            System.out.println(e.getMessage());
            throw new HelperBeanParserException();
        } catch (Exception e) {
            System.out.println("Saving user failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            throw new InternalErrorException();
        }
    }

    @Override
    public String deleteUser(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return userRepository.existsById(id) ? "User not deleted..." : "User with ID: " + id + " deleted.";
            } else {
                throw new UserNotFoundException();
            }
        } catch (Exception e) {
            System.out.println("Deleting user failed. Returning empty object. See stack trace.");
            System.out.println(e.getMessage());
            return userRepository.existsById(id) ? "User not deleted..." : "User with ID: " + id + " deleted.";
        }
    }

}
