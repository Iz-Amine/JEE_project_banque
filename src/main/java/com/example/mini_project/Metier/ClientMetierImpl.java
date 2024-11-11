package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Client;
import com.example.mini_project.Dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientMetierImpl implements ClientMetier {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client c) {
        // TODO Auto-generated method stub
        return clientRepository.save(c);
    }

    @Override
    public List<Client> listClient() {
        // TODO Auto-generated method stub
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            // Set fields to update here
            updatedClient.setNomClient(client.getNomClient());
            // Add other fields as needed
            return clientRepository.save(updatedClient);
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
