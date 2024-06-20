package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotExistsException;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class InMemoryItemStorage implements ItemStorage {
  HashMap<Integer, ItemDao> items = new HashMap<>();

  @Override
  public ArrayList<ItemDao> search(String text) {
    if (text == null || text.isEmpty()) {
      return new ArrayList<>();
    }
    ArrayList<ItemDao> result = new ArrayList<>();
    items.values().stream().filter(item -> item.isAvailable() && item.getName().toLowerCase().contains(text.toLowerCase())).forEach(result::add);
    return result;
  }

  @Override
  public ArrayList<ItemDao> findAllByUserId(int userId) {
    ArrayList<ItemDao> result = new ArrayList<>();
    items.values().stream().filter(item -> item.getOwner() == userId).forEach(result::add);
    return new ArrayList<>(result);
  }

  @Override
  public ItemDao findById(int id) {
    checkItemId(id);
    return items.get(id);
  }

  @Override
  public ItemDao create(ItemDao item) {
    int id = Util.findMaxId(items.keySet()) + 1;
    item.setId(id);
    items.put(id, item);
    return findById(id);
  }

  @Override
  public ItemDao patch(ItemDao item) {
    int id = item.getId();
    checkItemId(id);
    items.remove(id);
    items.put(id, item);
    return findById(id);
  }

  public void checkItemId(int id) {
    if (!items.containsKey(id)) {
      throw new NotExistsException("Такого id вещи не существует.");
    }
  }

}
