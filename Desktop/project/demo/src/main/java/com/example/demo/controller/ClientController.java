package com.example.demo.controller;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import   com.example.demo.entity.*;
import com.example.demo.service.ClientService;
import com.example.demo.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CompteService comtpteService;

    @GetMapping("list")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.listClients());
        return "client/list";
    }

    @GetMapping("/add")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/add";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute("client") Client client) {
        clientService.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientId}/comptes")
    public String viewClientComptes(@PathVariable Long clientId, Model model) {
        List<Compte> comptes = comtpteService.findByClientId(clientId);
        model.addAttribute("comptes", comptes);
        return "listClientComptes";
    }







}
