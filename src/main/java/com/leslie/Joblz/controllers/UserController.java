package com.leslie.Joblz.controllers;


import com.leslie.Joblz.dtos.UserDto;
import com.leslie.Joblz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    //create user
    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.addUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById( @PathVariable("id") UUID UserId){
        UserDto userDto = userService.getUserById(UserId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //update user
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") UUID userId,@RequestBody UserDto updatedUserDto){
        UserDto userDto = userService.updateUser(userId,updatedUserDto);
        return ResponseEntity.ok(userDto);
    }

    //delete user
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
