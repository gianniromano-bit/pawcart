package com.pawcart.web;

import com.pawcart.model.PetType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be 100 characters or less")
    private String name;

    @Size(max = 500, message = "Description must be 500 characters or less")
    private String description;

    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @DecimalMax(value = "9999.99", message = "Price must be under 10,000")
    private BigDecimal price;

    @NotNull(message = "Choose a pet type")
    private PetType petType;

    @NotNull(message = "Choose a brand")
    private Long brandId;

    @NotNull(message = "Choose a category")
    private Long categoryId;

    private String imageUrl;
}
