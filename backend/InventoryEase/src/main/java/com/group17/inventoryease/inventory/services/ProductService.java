package com.group17.inventoryease.inventory.services;

import com.group17.inventoryease.inventory.dtos.ProductDTO;
import com.group17.inventoryease.inventory.repositories.ProductRepository;
import com.group17.inventoryease.inventory.models.Product;
import com.group17.inventoryease.inventory.models.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProductsWithSuppliers(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for(Product product : products){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setCanExpire(product.getCanExpire());
            productDTO.setTotalQty(product.getTotalQuantity());
            productDTO.setThresholdMin(product.getThresholdMin());

            Set<ProductDTO.SupplierDTO> supplierDTOSet = new HashSet<>();
            for(Supplier supplier : product.getSuppliers()){
                ProductDTO.SupplierDTO supplierDTO = new ProductDTO.SupplierDTO(
                        String.valueOf(supplier.getSupplierId()),
                        supplier.getSupplierName()
                );
                supplierDTOSet.add(supplierDTO);
            }

            productDTO.setSuppliers(supplierDTOSet);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    public void updateQuantity(String productId, int qty){
        Product product = productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        int newTotalQty = product.getTotalQuantity() + qty;
        product.setTotalQuantity(newTotalQty);
        productRepository.save(product);
    }
}