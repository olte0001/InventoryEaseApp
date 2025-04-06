package com.group17.inventoryease.inventory.controllers;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getAllProductsWithSuppliers(){
        return productService.getAllProductsWithSuppliers();
    }
}