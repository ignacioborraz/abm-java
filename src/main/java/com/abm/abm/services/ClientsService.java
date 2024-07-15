package com.abm.abm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.abm.entities.Client;
import com.abm.abm.repositories.ClientsRepository;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository clientRepository;

    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        client.setName(clientDetails.getName());
        client.setDocnumber(clientDetails.getDocnumber());
        return clientRepository.save(client);
    }
}
