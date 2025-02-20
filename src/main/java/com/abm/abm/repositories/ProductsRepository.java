package com.abm.abm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.entities.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
}