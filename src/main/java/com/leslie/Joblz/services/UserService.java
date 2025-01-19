package com.leslie.Joblz.services;

import com.leslie.Joblz.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
   UserDto addUser(UserDto userDto);
   UserDto getUserById(UUID userId);
   List<UserDto> getAllUsers();
   UserDto updateUser(UUID userId, UserDto updatedUserDto);
   void deleteUser(UUID userId);
}
