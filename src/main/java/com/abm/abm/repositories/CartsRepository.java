package com.abm.abm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.entities.Cart;

@Repository
public interface CartsRepository extends JpaRepository<Cart, Long> {
}