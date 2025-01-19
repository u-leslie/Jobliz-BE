package com.leslie.Joblz.serviceImpl;

import com.leslie.Joblz.dtos.UserDto;
import com.leslie.Joblz.entities.User;
import com.leslie.Joblz.exceptions.NotFound;
import com.leslie.Joblz.mappers.UserMapper;
import com.leslie.Joblz.repositories.UserRepository;
import com.leslie.Joblz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(UUID userId) {
      User user = userRepository.findById(userId)
              .orElseThrow(()->
            new NotFound("User with given id not found"+userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto updatedUserDto) {
       User user= userRepository.findById(userId).orElseThrow(
                () -> new NotFound("User with given id not found"+userId)
        );
       user.setFirstName(updatedUserDto.getFirstName());
       user.setLastName(updatedUserDto.getLastName());
       user.setEmail(updatedUserDto.getEmail());
       user.setPassword(updatedUserDto.getPassword());
       user.setRole(updatedUserDto.getRole());
       user.setProfilePicture(updatedUserDto.getProfilePicture());
      User updatedUser = userRepository.save(user);
      return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new NotFound("User with given id not found"+userId)
        );
        userRepository.deleteById(userId);
    }
}
