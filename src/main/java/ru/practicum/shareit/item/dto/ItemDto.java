package ru.practicum.shareit.item.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
@ToString
public class ItemDto {
  private int id;
  @NotNull(message = "Отсутствует обязательный параметр name.")
  @NotEmpty(message = "Название не может быть пустым.")
  private String name;
  @NotNull(message = "Отсутствует обязательный параметр description.")
  @NotEmpty(message = "Описание не может быть пустым.")
  private String description;
  @NotNull(message = "Отсутствует обязательный параметр available.")
  private boolean available;
  private int owner = 0;
}
