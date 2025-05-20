package com.feirinha.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feirinha.api.DTO.ItemDto;
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

    public ItemModel create(ItemDto item) {
        
        if (itemRepository.existsByName(item.getName())) {
        return null;
        }   
        ItemModel newItem =new ItemModel(item);
        return itemRepository.save(newItem);
    }

    public ItemModel update(Long id, ItemModel updatedItem) {
    Optional<ItemModel> existingItemOptional = itemRepository.findById(id);

    if (existingItemOptional.isPresent()) {
        ItemModel existingItem = existingItemOptional.get();
        existingItem.setName(updatedItem.getName());
        existingItem.setQuantity(updatedItem.getQuantity());

        return itemRepository.save(existingItem);
    } else {
        return null;
        }
    }
    public void delete(Long id) {
    ItemModel item = findById(id);
    if (item != null) {
        itemRepository.delete(item);
    } 
}

}
