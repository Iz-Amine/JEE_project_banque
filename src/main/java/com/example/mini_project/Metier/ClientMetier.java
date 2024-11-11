package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Client;

import java.util.List;

public interface ClientMetier {
    public Client saveClient(Client c);
    public List<Client> listClient();

    public Client getClientById(Long id);
    public Client updateClient(Long id, Client client);
    public void deleteClient(Long id);
}
