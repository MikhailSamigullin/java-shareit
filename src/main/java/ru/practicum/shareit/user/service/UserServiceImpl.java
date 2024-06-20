package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserCustomDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final UserStorage userStorage;

  @Autowired
  public UserServiceImpl(UserStorage userStorage) {
    this.userStorage = userStorage;
  }

  public Optional<UserDto> findById(int id) {
    return Optional.of(UserMapper.toDto(userStorage.findById(id)));
  }

  public ArrayList<UserDto> findAll() {
    return UserMapper.toDto(userStorage.findAll());
  }

  public Optional<UserDto> create(UserDto user) {
    return Optional.of(UserMapper.toDto(userStorage.create(UserMapper.toDao(user))));
  }

  public Optional<UserDto> patch(UserCustomDto user, int userId) {
    Optional<UserDto> userDto = findById(userId);
    UserDto newUser = new UserDto();
    if (userDto.isPresent()) {
      newUser = userDto.get();
      if (user.getEmail() != null && !user.getEmail().isEmpty()) {
        newUser.setEmail(user.getEmail());
      }
      if (user.getName() != null && !user.getName().isEmpty()) {
        newUser.setName(user.getName());
      }
    }
    UserDao userDao = userStorage.patch(UserMapper.toDao(newUser));
    return Optional.of(UserMapper.toDto(Objects.requireNonNull(userDao)));
  }

  public ArrayList<UserDto> delete(int userId) {
    userStorage.delete(userId);
    return new ArrayList<>();
  }

}
