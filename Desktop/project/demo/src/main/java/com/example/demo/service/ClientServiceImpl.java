package com.example.demo.service;



import com.example.demo.dao.ClientRepository;
import com.example.demo.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }
}

