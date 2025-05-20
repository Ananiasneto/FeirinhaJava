package com.feirinha.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.model.ItemModel;
import com.feirinha.api.services.ItemServices;

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


}