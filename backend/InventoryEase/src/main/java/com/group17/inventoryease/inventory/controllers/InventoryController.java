package com.group17.inventoryease.inventory.controllers;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/products")
    public List<ProductDTO> getAllProductsWithSuppliers(){
        return productService.getAllProductsWithSuppliers();
    }

    @POST("api/inventory/receive")
    public ResponseEntity<Void> receiveItem(@RequestBody ReceiveItemDTO item) {
           itemService.receiveItem(item);
           productService.updateQuantity(item.getProductId(), item.getItemQuantity());
           return ResponseEntity.ok().build();
    }
}