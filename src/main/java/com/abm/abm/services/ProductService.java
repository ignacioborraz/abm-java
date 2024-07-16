package com.abm.abm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.abm.entities.Product;
import com.abm.abm.repositories.ProductsRepository;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("Products not found");
        } else {
            return products;
        }
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found");
        }
        
    }

    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            if (productDetails.getTitle() != null) {
                foundProduct.setTitle(productDetails.getTitle());
            }
            if (productDetails.getStock() != null) {
                foundProduct.setStock(productDetails.getStock());
            }
            if (productDetails.getPrice() != null) {
                foundProduct.setPrice(productDetails.getPrice());
            }
            return productRepository.save(foundProduct);
        } else {
            throw new RuntimeException("Product not found");
        }

    }
}
