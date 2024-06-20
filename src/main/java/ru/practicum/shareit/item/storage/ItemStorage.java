package ru.practicum.shareit.item.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dao.ItemDao;

import java.util.ArrayList;

@Component
public interface ItemStorage {
  ArrayList<ItemDao> search(String text);

  ArrayList<ItemDao> findAllByUserId(int userId);

  ItemDao findById(int id);

  ItemDao create(ItemDao item);

  ItemDao patch(ItemDao item);

}
