package com.group17.inventoryease.dtos;

import java.util.Set;

public class ProductDTO {
    private Long productId;
    private String productName;
    private Boolean canExpire;
    private Set<SupplierDTO> suppliers;

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getCanExpire() {
        return canExpire;
    }
    public void setCanExpire(Boolean canExpire) {
        this.canExpire = canExpire;
    }

    public Set<SupplierDTO> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(Set<SupplierDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public static class SupplierDTO {
        private String supplierId;
        private String supplierName;

        public SupplierDTO(String supplierId, String supplierName) {
            this.supplierId = supplierId;
            this.supplierName = supplierName;
        }

        public String getSupplierId() {
            return supplierId;
        }
        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }
        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }
    }
}
