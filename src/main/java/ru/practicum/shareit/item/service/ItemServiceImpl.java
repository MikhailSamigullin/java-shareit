package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemCustomDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
  private final ItemStorage itemStorage;
  private final UserStorage userStorage;

  @Autowired
  public ItemServiceImpl(ItemStorage itemStorage, UserStorage userStorage) {
    this.itemStorage = itemStorage;
    this.userStorage = userStorage;
  }

  public Optional<ItemDto> findById(int id) {
    return Optional.of(ItemMapper.toDto(itemStorage.findById(id)));
  }

  public ArrayList<ItemDto> search(String text) {
    return ItemMapper.toDto(itemStorage.search(text));
  }

  public ArrayList<ItemDto> findAllByUserId(int userId) {
    return ItemMapper.toDto(itemStorage.findAllByUserId(userId));
  }

  public Optional<ItemDto> create(ItemDto item, int userId) {
    System.out.println(item);
    userStorage.checkUserId(userId);
    item.setOwner(userId);
    return Optional.of(ItemMapper.toDto(itemStorage.create(ItemMapper.toDao(item))));
  }

  public Optional<ItemDto> patch(ItemCustomDto item, int userId, int itemId) {
    Optional<ItemDto> itemDto = findById(itemId);
//    ItemDto newItem = new ItemDto();
    ItemDto newItem = null;
    checkUserId(userId);
    if (itemDto.isPresent()) {
      newItem = itemDto.get();
      if (item.getDescription() != null && !item.getDescription().isEmpty()) {
        newItem.setDescription(item.getDescription());
      }
      if (item.getName() != null && !item.getName().isEmpty()) {
        newItem.setName(item.getName());
      }
      if (item.isAvailable()) {
        newItem.setAvailable(true);
      } else {
        newItem.setAvailable(false);
      }
    }
    assert newItem != null;
    ItemDao itemDao = itemStorage.patch(ItemMapper.toDao(newItem));
    return Optional.of(ItemMapper.toDto(Objects.requireNonNull(itemDao)));
  }

  private void checkUserId(int userId) {
    if (userId == 0) {
      throw new ConflictException("Не передан заголовок - X-Sharer-User-Id");
    }
  }

}
