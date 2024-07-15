package com.abm.abm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.abm.entities.Client;
import com.abm.abm.entities.Invoice;
import com.abm.abm.repositories.ClientsRepository;
import com.abm.abm.repositories.InvoicesRepository;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesRepository invoiceRepository;

    @Autowired
    private ClientsRepository clientRepository;

    public Invoice generateInvoice(Long clientId, Double total) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice.setTotal(total);

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getInvoices();
    }
}
