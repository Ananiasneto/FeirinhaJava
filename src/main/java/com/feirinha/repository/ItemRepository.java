package com.feirinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.feirinha.model.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
}
