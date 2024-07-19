package com.abm.abm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.abm.entities.Invoice;
import com.abm.abm.services.InvoicesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoicesController {

    @Autowired
    private InvoicesService invoiceService;

    @Operation(summary = "Generate an invoice", description = "Generates an invoice for the client's cart with the total amount")
    @ApiResponse(responseCode = "200", description = "Invoice generated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Client o carts not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping
    public ResponseEntity<Invoice> generateInvoice(@RequestParam Long clientId) {
        try {
            Invoice invoice = invoiceService.generateInvoice(clientId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
        } catch (RuntimeException e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }  
    }

    @Operation(summary = "Get invoices by client ID", description = "Retrieves a list of invoices for the specified client")
    @ApiResponse(responseCode = "200", description = "Invoices retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{clientId}")
    public ResponseEntity<Invoice> getInvoicesByClientId(@PathVariable Long clientId) {
        try {
            Invoice invoice = invoiceService.getInvoicesByClientId(clientId);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }
}