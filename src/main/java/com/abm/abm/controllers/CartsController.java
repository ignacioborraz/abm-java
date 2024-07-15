package com.abm.abm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.abm.entities.Cart;
import com.abm.abm.services.CartsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/carts")
public class CartsController {

    @Autowired
    private CartsService cartService;

    @Operation(summary = "Add product to cart", description = "Adds a product to the client's cart with the specified quantity")
    @ApiResponse(responseCode = "200", description = "Product added to cart successfully", 
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "404", description = "Client or product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/{cid}/{pid}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cid, @PathVariable Long pid, @PathVariable Integer quantity) {
        try {
            Cart cart = cartService.addProductToCart(cid, pid, quantity);
            return ResponseEntity.ok(cart);
        }catch (RuntimeException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }       
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }        
    }

    @Operation(summary = "Remove product from cart", description = "Removes a product from the client's cart")
    @ApiResponse(responseCode = "204", description = "Product removed from cart successfully")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId) {
        cartService.removeProductFromCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
