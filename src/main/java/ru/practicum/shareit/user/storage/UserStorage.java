package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.ArrayList;

@Component
public interface UserStorage {
  ArrayList<UserDao> findAll();

  UserDao findById(int id);

  UserDao create(UserDao user);

  UserDao patch(UserDao user);

  void delete(int userId);

  void checkUserId(int userId);
}
