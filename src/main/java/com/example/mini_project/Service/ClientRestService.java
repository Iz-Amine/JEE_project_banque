package com.example.mini_project.Service;

import com.example.mini_project.Entity.Client;
import com.example.mini_project.Metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestService {
    @Autowired
    private ClientMetier clientMetier;
//    @RequestMapping(value="/clients",method= RequestMethod.POST)
//    public Client saveClient(@RequestBody Client c) {
//        return clientMetier.saveClient(c);
//    }
//    @RequestMapping(value="/clients",method=RequestMethod.GET)
//    public List<Client> listClient() {
//        return clientMetier.listClient();
//    }

    // Create a new client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientMetier.saveClient(client);
        return ResponseEntity.ok(savedClient);
    }

    // Get a client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientMetier.getClientById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientMetier.listClient();
    }

    // Update a client by ID
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updatedClient = clientMetier.updateClient(id, client);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }

    // Delete a client by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientMetier.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
