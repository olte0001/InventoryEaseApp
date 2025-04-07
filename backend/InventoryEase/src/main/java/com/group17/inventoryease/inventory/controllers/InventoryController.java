package com.group17.inventoryease.inventory.controllers;

import com.group17.inventoryease.inventory.dtos.ProductDTO;
import com.group17.inventoryease.inventory.dtos.ReceiveItemDTO;
import com.group17.inventoryease.inventory.services.ItemService;
import com.group17.inventoryease.inventory.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/products")
    public List<ProductDTO> getAllProductsWithSuppliers(){
        return productService.getAllProductsWithSuppliers();
    }

    @PostMapping("/receive")
    public ResponseEntity<Void> receiveItem(@RequestBody ReceiveItemDTO item) {
           itemService.receiveItem(item);
           productService.updateQuantity(item.getProductId(), item.getItemQuantity());
           return ResponseEntity.ok().build();
    }
}