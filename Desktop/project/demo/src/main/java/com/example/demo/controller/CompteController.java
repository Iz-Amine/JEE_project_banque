package com.example.demo.controller;

import com.example.demo.entity.Compte;
import com.example.demo.entity.CompteCourant;
import com.example.demo.entity.CompteEpargne;
import com.example.demo.service.ClientService;
import com.example.demo.service.CompteService;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeService employeService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {




        model.addAttribute("clients", clientService.listClients());

        if ( clientService.listClients() == null ) {
            // Check if there are clients in the database
            System.out.println("No clients found in the database.");
        }
        model.addAttribute("employes", employeService.listAll());
        return "createCompte";
    }

    @PostMapping("/create")
    public String createCompte(
            @RequestParam String typeCompte,
            @RequestParam String codeCompte,
            @RequestParam double solde,
            @RequestParam double tauxOrDecouvert,
            @RequestParam Long clientId,
            @RequestParam Long employeId,
            Model model) {

        Compte compte;

        if ("courant".equalsIgnoreCase(typeCompte)) {
            compte = new CompteCourant(codeCompte, new Date(), solde, tauxOrDecouvert);
        } else if ("epargne".equalsIgnoreCase(typeCompte)) {
            compte = new CompteEpargne(codeCompte, new Date(), solde, tauxOrDecouvert);
        } else {
            model.addAttribute("error", "Invalid account type");
            return "createCompte";
        }

        compteService.addCompte(compte, clientId, employeId);
        return "redirect:/comptes";
    }

    @GetMapping("list")
    public String listComptes(Model model) {
        List<Compte> comptes = compteService.listAll();
        model.addAttribute("comptes", comptes);
        return "listComptes";
    }
}