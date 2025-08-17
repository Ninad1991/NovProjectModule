package com.scaler.novprojectmodule.dto;

public class ProductDTO {
        private Long id;
        private String title;
        private String description;
        private Double price;
        private String imageUrl;
        private Long product_id;

        // Constructor
        public ProductDTO(Long id, String title, String description, Double price, String imageUrl, Long product_id) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
            this.product_id = product_id;

        }

        // Getters
        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public Double getPrice() { return price; }
        public String getImageUrl() { return imageUrl; }
    public Long getProduct_id() { return product_id; }
    }


