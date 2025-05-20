package com.feirinha.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.DTO.ItemDto;
import com.feirinha.api.model.ItemModel;
import com.feirinha.api.services.ItemServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemServices itemServices;

    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }
    @GetMapping
   public ResponseEntity<List<ItemModel>> getAllItems() {
    List<ItemModel> items = itemServices.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(items);
}
@GetMapping("/{id}")
   public ResponseEntity<ItemModel> getItemsById(@PathVariable("id") long id) {
    if (id<0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    ItemModel items = itemServices.findById(id);
    if(items!=null){
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }else{
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
   
}

@PostMapping
public ResponseEntity<ItemModel> createItem(@RequestBody @Valid ItemDto body) {
    ItemModel newItem = itemServices.create(body);
    if(newItem==null){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
}

@PutMapping("/{id}")
public ResponseEntity<ItemModel> updateItem(@PathVariable("id") Long id, @RequestBody @Valid ItemDto body) {
    if (id < 0) {
        return ResponseEntity.badRequest().build();
    }
    Optional<ItemModel> updatedItemOptional = itemServices.update(id, body);
    if (updatedItemOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    ItemModel updatedItem = updatedItemOptional.get();
    if (!updatedItem.getId().equals(id)) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    if (id < 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    ItemModel exist=itemServices.findById(id);
    if(exist==null){
     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    itemServices.delete(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}

}