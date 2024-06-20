package ru.practicum.shareit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.user.dto.UserCustomDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  private final UserServiceImpl userService;

  @Autowired
  public UserController(final UserServiceImpl userServiceImpl) {
    this.userService = userServiceImpl;
  }

  @GetMapping("{userId}")
  public Optional<UserDto> findById(@PathVariable String userId) {
    return userService.findById(Integer.parseInt(userId));
  }

  @GetMapping
  public ArrayList<UserDto> findAll() {
    return userService.findAll();
  }

  @PostMapping
  public Optional<UserDto> create(@Valid @RequestBody UserDto user) {
    return userService.create(user);
  }

  @PatchMapping("{userId}")
  public Optional<UserDto> patch(@PathVariable int userId, @Valid @RequestBody UserCustomDto user) {
    return userService.patch(user, userId);
  }

  @DeleteMapping("/{userId}")
  public ArrayList<UserDto> deleteFriend(@PathVariable int userId) {
    return userService.delete(userId);
  }
}
