package com.example.demo.service;


import com.example.demo.entity.*
        ;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    Client findClientById(Long clientId);
    List<Client> listClients();
}

