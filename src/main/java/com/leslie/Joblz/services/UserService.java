package com.leslie.Joblz.services;

import com.leslie.Joblz.dtos.UserDto;

import java.util.List;

public interface UserService {
   UserDto addUser(UserDto userDto);
   UserDto getUserById(Long userId);
   List<UserDto> getAllUsers();
   UserDto updateUser(Long userId, UserDto updatedUserDto);
   void deleteUser(Long userId);
}
