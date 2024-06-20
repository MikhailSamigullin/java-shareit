package ru.practicum.shareit.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.ItemCustomDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  private final ItemServiceImpl itemService;

  @Autowired
  public ItemController(final ItemServiceImpl itemServiceImpl) {
    this.itemService = itemServiceImpl;
  }

  @GetMapping("{itemId}")
  public Optional<ItemDto> findById(@PathVariable String itemId) {
    return itemService.findById(Integer.parseInt(itemId));
  }

  @GetMapping("/search")
  public ArrayList<ItemDto> search(@RequestParam(name = "text") String text) {
    return itemService.search(text);
  }

  @GetMapping
  public ArrayList<ItemDto> findAllByUserId(@RequestHeader("X-Sharer-User-Id") int userId) {
    return itemService.findAllByUserId(userId);
  }

  @PostMapping
  public Optional<ItemDto> create(@Valid @RequestBody ItemDto item, @RequestHeader("X-Sharer-User-Id") int userId) {
    return itemService.create(item, userId);
  }

  @PatchMapping("{itemId}")
  public Optional<ItemDto> patch(@PathVariable int itemId, @Valid @RequestBody ItemCustomDto item, @RequestHeader("X-Sharer-User-Id") int userId) {
    return itemService.patch(item, userId, itemId);
  }

}
