package ru.practicum.shareit.user.mapper;

import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;

public class UserMapper {
  public static UserDao toDao(final UserDto user) {
    return new UserDao(user.getId(),
            user.getEmail(),
            user.getName());
  }

  public static UserDto toDto(final UserDao user) {
    return new UserDto(user.getId(),
            user.getEmail(),
            user.getName());
  }

  public static ArrayList<UserDto> toDto(final ArrayList<UserDao> users) {
    ArrayList<UserDto> userDto = new ArrayList<>();
    for (UserDao user : users) {
      userDto.add(toDto(user));
    }
    return userDto;
  }
}
