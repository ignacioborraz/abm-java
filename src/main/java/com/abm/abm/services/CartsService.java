package com.abm.abm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.abm.entities.Cart;
import com.abm.abm.entities.Client;
import com.abm.abm.entities.Product;
import com.abm.abm.repositories.CartsRepository;
import com.abm.abm.repositories.ClientsRepository;
import com.abm.abm.repositories.ProductsRepository;

@Service
public class CartsService {

    @Autowired
    private CartsRepository cartRepository;

    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private ClientsRepository clientRepository;

    public Cart addProductToCart(Long clientId, Long productId, Integer amount) {
        Optional<Client> client = clientRepository.findById(clientId);
        Optional<Product> product = productRepository.findById(productId);
        if (client.isPresent() & product.isPresent()) {
            Cart cart = new Cart();
            cart.setClient(client.get());
            cart.setProduct(product.get());
            cart.setPrice(product.get().getPrice());
            cart.setAmount(amount);
            cart.setDelivered(false);
            return cartRepository.save(cart);
        } else {
            throw new RuntimeException("Client or Product not found");
        }
    }

    public Cart removeProductFromCart(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            cartRepository.deleteById(cartId);
            return cart.get();
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    public List<Cart> findByClientIdAndDelivered(Long clientId) {
        List<Cart> carts = cartRepository.findByClientIdAndDelivered(clientId, false);
        if (carts.isEmpty()) {
            throw new RuntimeException("Carts not found");
        } else {
            return carts;
        }
    }
}
