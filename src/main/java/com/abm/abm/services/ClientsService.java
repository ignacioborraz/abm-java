package com.abm.abm.services;

import java.util.Optional;

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
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client foundClient = client.get();
            if (clientDetails.getName() != null) {
                foundClient.setName(clientDetails.getName());
            }
            if (clientDetails.getDocnumber() != null) {
                foundClient.setDocnumber(clientDetails.getDocnumber());
            }
            return clientRepository.save(foundClient);
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
