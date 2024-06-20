package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.ArrayList;

public class ItemMapper {
  public static ItemDao toDao(final ItemDto item) {
    return new ItemDao(item.getId(),
            item.getName(),
            item.getDescription(),
            item.isAvailable(),
            item.getOwner());
  }

  public static ItemDto toDto(final ItemDao item) {
    return new ItemDto(item.getId(),
            item.getName(),
            item.getDescription(),
            item.isAvailable(),
            item.getOwner());
  }

  public static ArrayList<ItemDto> toDto(final ArrayList<ItemDao> items) {
    ArrayList<ItemDto> itemDto = new ArrayList<>();
    for (ItemDao item : items) {
      itemDto.add(toDto(item));
    }
    return itemDto;
  }
}
