package com.abm.abm.services;

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

    public Cart addProductToCart(Long clientId, Long productId, int quantity) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = new Cart();
        cart.setClient(client);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
