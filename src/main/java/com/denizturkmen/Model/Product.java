package com.denizturkmen.Model;

public class Product {

    public String ProductName;
    public int SupplierId;
    public int CategoryId;
    public String QuantityPerUnit;
    public String UnitPrice;
    public String UnitInStock;
    public String UnitOnOrder;
    public int ReorderLevel;
    public boolean Discontinued;


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        QuantityPerUnit = quantityPerUnit;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getUnitInStock() {
        return UnitInStock;
    }

    public void setUnitInStock(String unitInStock) {
        UnitInStock = unitInStock;
    }

    public String getUnitOnOrder() {
        return UnitOnOrder;
    }

    public void setUnitOnOrder(String unitOnOrder) {
        UnitOnOrder = unitOnOrder;
    }

    public int getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        ReorderLevel = reorderLevel;
    }

    public boolean isDiscontinued() {
        return Discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        Discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductName='" + ProductName + '\'' +
                ", SupplierId=" + SupplierId +
                ", CategoryId=" + CategoryId +
                ", QuantityPerUnit='" + QuantityPerUnit + '\'' +
                ", UnitPrice='" + UnitPrice + '\'' +
                ", UnitInStock='" + UnitInStock + '\'' +
                ", UnitOnOrder='" + UnitOnOrder + '\'' +
                ", ReorderLevel=" + ReorderLevel +
                ", Discontinued=" + Discontinued +
                '}';
    }
}
