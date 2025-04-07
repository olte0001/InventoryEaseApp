package com.group17.inventoryease.inventory.services;

import com.group17.inventoryease.inventory.dtos.ProductDTO;

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
            productDTO.setTotalQty(product.getTotalQty());
            productDTO.setThresholdMin(product.getThresholdMin());

            Set<ProductDTO.SupplierDTO> supplierDTOSet = new HashSet<>();
            for(Supplier supplier : product.getSuppliers()){
                ProductDTO.SupplierDTO supplierDTO = new ProductDTO.SupplierDTO();
                supplierDTO.setSupplierId(supplier.getSupplierId());
                supplierDTO.setSupplierName(supplier.getSupplierName());
                supplierDTOSet.add(supplierDTO);
            }

            productDTO.setSuppliers(supplierDTOSet);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    public void updateQuantity(String productId, int qty){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        int newTotalQty = product.getTotalQuantity() + qty;
        product.setTotalQuantity(newTotalQty);
        productRepository.save(product);
    }
}