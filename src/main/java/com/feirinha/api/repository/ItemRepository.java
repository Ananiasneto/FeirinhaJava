package com.feirinha.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feirinha.api.model.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    boolean existsByName(String name);
}
