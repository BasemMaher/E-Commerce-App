package com.example.app_ecommerce.model;

public class Products {
    Integer productId;
    String productName;
    String Title;
    Integer Price;
    String Storage;
    Integer imageUrl;

    public Products(Integer productId, String productName, String title, Integer price, String storage, Integer imageUrl) {
        this.productId = productId;
        this.productName = productName;
        Title = title;
        Price = price;
        Storage = storage;
        this.imageUrl = imageUrl;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
