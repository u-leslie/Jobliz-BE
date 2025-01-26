package com.leslie.Joblz.mappers;

import com.leslie.Joblz.dtos.UserDto;
import com.leslie.Joblz.entities.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
       return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getProfilePicture(),
                user.getPassword(),
                user.getCreated()

        );
    }
    public static User mapToUser(UserDto userDto) {
          return new User(
                  userDto.getId(),
                  userDto.getFirstName(),
                  userDto.getLastName(),
                  userDto.getEmail(),
                  userDto.getPhone(),
                  userDto.getRole(),
                  userDto.getProfilePicture(),
                  userDto.getPassword(),
                  userDto.getCreated()
          );
    }
}
