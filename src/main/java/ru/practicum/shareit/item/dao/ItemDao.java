package ru.practicum.shareit.item.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class ItemDao {
  private int id;
  @NotNull(message = "Отсутствует обязательный параметр name.")
  @NotEmpty(message = "Название не может быть пустым.")
  private String name;
  @NotNull(message = "Отсутствует обязательный параметр description.")
  @NotEmpty(message = "Описание не может быть пустым.")
  private String description;
  @NotNull(message = "Отсутствует обязательный параметр available.")
  private boolean available;
  @NotNull(message = "Отсутствует обязательный параметр owner.")
  private int owner;
}
