package com.feirinha.api.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Column(unique = true)
    private String name;
    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 1)
    private Integer quantity;
}
