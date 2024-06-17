package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
  private int id;
  @Email(message = "Неверный формат email.")
  @NotNull(message = "Отсутствует обязательный параметр email.")
  @NotEmpty(message = "Параметр email не должен быть пустым.")
  private String email = "";
  @NotNull(message = "Отсутствует обязательный параметр name.")
  @NotEmpty(message = "Параметр name не должен быть пустым.")
  @Builder.Default
  private String name = "";
}
