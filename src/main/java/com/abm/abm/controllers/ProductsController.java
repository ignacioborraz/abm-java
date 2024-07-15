package com.abm.abm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.abm.entities.Product;
import com.abm.abm.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponse(responseCode = "200", description = "Product created successfully", 
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        System.out.println(product);
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully", 
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get a product by ID", description = "Retrieves a product by its ID")
    @ApiResponse(responseCode = "200", description = "Product retrieved successfully", 
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pid) {
        return productService.getProductById(pid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a product by ID", description = "Updates a product by its ID with the provided details")
    @ApiResponse(responseCode = "200", description = "Product updated successfully", 
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long pid, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(pid, product));
    }
}