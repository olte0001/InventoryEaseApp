package com.group17.inventoryease.inventory.services;

import com.group17.inventoryease.ums.repositories.LocationRepository;
import com.group17.inventoryease.inventory.models.Locator;

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
        Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Supplier supplier = supplierRepository.findById(item.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Supplier location = locationRepository.findById(item.getLocationId())
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

    public String generateSerialNumber(){
        // TODO: implementation of generateSerialNumber();
    }
}