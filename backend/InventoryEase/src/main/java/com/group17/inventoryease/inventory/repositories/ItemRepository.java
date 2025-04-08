package com.group17.inventoryease.inventory.repositories;


import com.group17.inventoryease.inventory.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findTopByOrderByItemIdDesc();

}