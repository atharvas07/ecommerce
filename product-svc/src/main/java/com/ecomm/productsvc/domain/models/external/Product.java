package com.ecomm.productsvc.domain.models.external;

// Product Model
public class Product {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
    private int quantity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public Rating getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}



