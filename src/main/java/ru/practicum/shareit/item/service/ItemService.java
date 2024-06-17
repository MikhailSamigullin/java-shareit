package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemCustomDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {
  Optional<ItemDto> findById(int id);

  List<ItemDto> search(String text);

  List<ItemDto> findAllByUserId(int userId);

  Optional<ItemDto> create(ItemDto item, int userId);

  Optional<ItemDto> patch(ItemCustomDto user, int userId, int itemId);

}
