package com.abm.abm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiResponse(responseCode = "201", description = "Product added to cart successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Client or Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/{clid}/{pid}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long clid, @PathVariable Long pid, @PathVariable Integer quantity) {
        try {
            Cart cart = cartService.addProductToCart(clid, pid, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }catch (RuntimeException e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }       
        catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }        
    }

    @Operation(summary = "Remove product from cart", description = "Removes a product from the client's cart")
    @ApiResponse(responseCode = "204", description = "Product removed from cart successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId) {
        try {
            cartService.removeProductFromCart(cartId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }

    @Operation(summary = "Find carts by client ID and delivered status", description = "Returns a list of carts for a given client ID with delivered status as false")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of carts", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Carts not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{clid}")
    public ResponseEntity<List<Cart>> findByClientIdAndDelivered(@PathVariable Long clid) {
        try {
            List<Cart> carts = cartService.findByClientIdAndDelivered(clid);
            return ResponseEntity.ok(carts);
        }catch (RuntimeException e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }       
        catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }        
    }

}
