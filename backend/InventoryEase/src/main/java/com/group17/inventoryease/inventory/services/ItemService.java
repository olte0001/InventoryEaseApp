package com.group17.inventoryease.inventory.services;

import com.group17.inventoryease.inventory.models.Item;
import com.group17.inventoryease.inventory.models.Product;
import com.group17.inventoryease.inventory.models.Supplier;
import com.group17.inventoryease.inventory.models.Locator;
import com.group17.inventoryease.inventory.repositories.ItemRepository;
import com.group17.inventoryease.inventory.repositories.ProductRepository;
import com.group17.inventoryease.inventory.repositories.SupplierRepository;
import com.group17.inventoryease.ums.models.Location;
import com.group17.inventoryease.ums.repositories.LocationRepository;
import com.group17.inventoryease.inventory.dtos.ReceiveItemDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LocationRepository locationRepository;

    public void receiveItem(ReceiveItemDTO item){
        Product product = productRepository.findById(Long.valueOf(item.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Supplier supplier = supplierRepository.findById(Long.valueOf(item.getSupplierId()))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Location location = locationRepository.findById(Long.valueOf(item.getLocationId()))
                .orElseThrow(() -> new RuntimeException("Location not found"));

        Item itemDB = new Item();
        itemDB.setItemId(generateSerialNumber());
        itemDB.setItemQuantity(item.getQuantity());
        itemDB.setReceivedDate(item.getReceivedDate());
        itemDB.setProduct(product);
        itemDB.setSupplier(supplier);
        Locator locator = new Locator();
        locator.setLocation(location);
        itemDB.setLocator(locator);

        itemRepository.save(itemDB);
    }

    public Long generateSerialNumber(){
        // TODO: implementation of generateSerialNumber();
        return null;
    }
}