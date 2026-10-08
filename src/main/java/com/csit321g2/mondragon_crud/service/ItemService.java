package com.csit321g2.mondragon_crud.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.mondragon_crud.entity.ItemEntity;
import com.csit321g2.mondragon_crud.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository irepo;

    public ItemService() {
    }

    // C - Create
    public ItemEntity postItem(ItemEntity Item) {
        return irepo.save(Item);
    }

    // R - Read
    public List<ItemEntity> getAllItems() {
        return irepo.findAll();
    }

    // U - Update
    @SuppressWarnings("finally")
    public ItemEntity putItem(int id, ItemEntity newItemDetails) {
        ItemEntity Item = new ItemEntity();
        try {
            Item = irepo.findById(id).get();
            Item.setName(newItemDetails.getName());
            Item.setUnit(newItemDetails.getUnit());
            Item.setPrice(newItemDetails.getPrice());
        } catch (NoSuchElementException ex) {
            Item = null;
        } finally {
            return (Item != null) ? irepo.save(Item) : null;
        }
    }

    // D - Delete
    public String deleteItem(int id) {
        String msg = "";
        if (irepo.findById(id).isPresent()) {
            irepo.deleteById(id);
            msg = "Item " + id + " is successfully deleted!";
        } else {
            msg = id + " does not exist!";
        }
        return msg;
    }
}