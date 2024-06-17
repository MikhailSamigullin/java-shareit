package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotExistsException;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class InMemoryUserStorage implements UserStorage {
  HashMap<Integer, UserDao> users = new HashMap<>();

  @Override
  public ArrayList<UserDao> findAll() {
    return new ArrayList<>(users.values());
  }

  @Override
  public UserDao findById(int id) {
    checkUserId(id);
    return users.get(id);
  }

  @Override
  public UserDao create(UserDao user) {
    checkEmail(user.getEmail());
    int id = Util.findMaxId(users.keySet()) + 1;
    user.setId(id);
    users.put(id, user);
    return findById(id);
  }

  @Override
  public UserDao patch(UserDao user) {
    int id = user.getId();
    checkUserId(id);
    checkEmail(user.getEmail());
    users.remove(id);
    users.put(id, user);
    return findById(id);
  }

  @Override
  public void delete(int userId) {
    users.remove(userId);
  }

  public void checkUserId(int id) {
    if (!users.containsKey(id)) {
      throw new NotExistsException("Такого id пользователя не существует.");
    }
  }

  public void checkEmail(String email) {
    long count = users.values().stream().filter(item -> item.getEmail().equals(email)).count();
    if (count != 0) {
      throw new ConflictException("Такой email уже используется.");
    }
  }
}
