package com.feirinha.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feirinha.api.model.ItemModel;
import com.feirinha.api.repository.ItemRepository;


@Service
public class ItemServices{

    private final ItemRepository itemRepository;

    public ItemServices(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

   public ItemModel findById(Long id) {
    Optional<ItemModel> optionalItem = itemRepository.findById(id);

    if (optionalItem.isPresent()) {
        return optionalItem.get();
    } else {
        return null;
    }
}

}
