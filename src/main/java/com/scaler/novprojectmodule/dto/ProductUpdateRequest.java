package com.scaler.novprojectmodule.dto;

import lombok.Data;

@Data  // Lombok annotation
public class ProductUpdateRequest {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;  // Match your entity field name
    private Long categoryId;  // For category association
}
