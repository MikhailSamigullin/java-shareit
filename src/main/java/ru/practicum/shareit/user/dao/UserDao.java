package ru.practicum.shareit.user.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class UserDao {
  private int id;
  @Email(message = "Неверный формат email.")
  @NotNull(message = "Отсутствует обязательный параметр email.")
  @NotEmpty(message = "Параметр email не должен быть пустым.")
  private String email;
  @NotNull(message = "Отсутствует обязательный параметр name.")
  @NotEmpty(message = "Параметр name не должен быть пустым.")
  private String name;
}
