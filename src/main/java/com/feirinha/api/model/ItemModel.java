package com.feirinha.api.model;

import com.feirinha.api.DTO.ItemDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "itensTable")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Column(unique = true)
    private String name;
    @NotNull(message = "A quantidade não pode ser nula")
    @Min (value = 1)
    private Integer quantity;

    public ItemModel(ItemDto item) {
    this.name = item.getName();
    this.quantity = item.getQuantity();
}
}