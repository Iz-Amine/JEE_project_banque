package com.example.mini_project.Controller;

import com.example.mini_project.Entity.Client;
import com.example.mini_project.Metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientMetier clientMetier;

    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientMetier.listClient();
        model.addAttribute("clients", clients);
        return "clients/list";
    }

    @GetMapping("/new")
    public String showCreateClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/create";
    }

    @PostMapping("/new")
    public String createClient(@ModelAttribute("client") Client client) {
        clientMetier.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable Long id, Model model) {
        Client client = clientMetier.getClientById(id);
        if (client == null) {
            return "errors/404";
        }
        model.addAttribute("client", client);
        return "clients/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientMetier.getClientById(id);
        if (client == null) {
            return "errors/404";
        }
        model.addAttribute("client", client);
        return "clients/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateClient(@PathVariable Long id, @ModelAttribute("client") Client client) {
        clientMetier.updateClient(id, client);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable Long id) {
        clientMetier.deleteClient(id);
        return "redirect:/clients";
    }
}
