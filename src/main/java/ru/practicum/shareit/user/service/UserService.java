package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserCustomDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
  Optional<UserDto> findById(int id);

  List<UserDto> findAll();

  Optional<UserDto> create(UserDto user);

  Optional<UserDto> patch(UserCustomDto user, int userId);

  ArrayList<UserDto> delete(int userId);
}
