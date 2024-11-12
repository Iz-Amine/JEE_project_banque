package com.example.mini_project.Controller;

import com.example.mini_project.Entity.*;
import com.example.mini_project.Metier.ClientMetier;
import com.example.mini_project.Metier.ClientMetierImpl;
import com.example.mini_project.Metier.CompteMetier;
import com.example.mini_project.Metier.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteMetier compteMetier;


@Autowired
private OperationService operationService  ;
    @Autowired
    private ClientMetier clientMetier;

    // Display all accounts
    @GetMapping
    public String listComptes(@RequestParam(value = "typeFilter", required = false) String typeFilter, Model model) {
        // Fetch all accounts from the service
        List<Compte> allComptes = compteMetier.listComptes();

        // Apply filtering on the list of accounts based on typeFilter
        List<Compte> filteredComptes = allComptes.stream()
                .filter(compte -> {
                    if ("courant".equalsIgnoreCase(typeFilter)) {
                        return compte instanceof CompteCourant;
                    } else if ("epargne".equalsIgnoreCase(typeFilter)) {
                        return compte instanceof CompteEpargne;
                    }
                    return true; // If no filter, include all accounts
                })
                .toList(); // Collect the filtered list

        // Add the filtered list and the current filter to the model
        model.addAttribute("comptes", filteredComptes);
        model.addAttribute("typeFilter", typeFilter);
        return "comptes/list";
    }


    // Show form for creating a new account
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("compte", new CompteCourant()); // Default to CompteCourant
        model.addAttribute("clients" , clientMetier.listClient()) ;
        return "comptes/create"; // Thymeleaf template for creating a new account
    }

    @PostMapping("/new")
    public String createCompte(@RequestParam("type") String type,
                               @RequestParam("codeCompte") String codeCompte,
                               @RequestParam("solde") double solde,
                               @RequestParam(required = false) Double decouvert,
                               @RequestParam(required = false) Double taux,
                               @RequestParam Client clientId,
                               Model model) {

        // Retrieve the Client object using clientId
        Client client = clientMetier.getClientById(clientId.getCodeClient());
        if (client == null) {
            model.addAttribute("error", "Client not found. Please select a valid client.");
            return "comptes/create";
        }

        Compte compte;
        Date dateCreation = new Date(); // Set the current date

        // Check type and required fields
        if ("courant".equalsIgnoreCase(type) && decouvert != null) {
            compte = new CompteCourant(codeCompte, dateCreation, solde, decouvert, client);
        } else if ("epargne".equalsIgnoreCase(type) && taux != null) {
            compte = new CompteEpargne(codeCompte, dateCreation, solde, taux, client);
        } else {
            // Return to form with error if the type is invalid or fields are missing
            model.addAttribute("error", "Type de compte invalide ou champs manquants. Veuillez remplir les informations requises.");
            return "comptes/create";
        }

        // Save the account if all fields are valid
        compteMetier.saveCompte(compte);
        return "redirect:/comptes"; // Redirect to the list of accounts after saving
    }
    // Display form for editing an account
    @GetMapping("/{id}/edit")
    public String editCompteForm(@PathVariable("id") String id, Model model) {
        Optional<Compte> compteOptional = compteMetier.getCompteById(id);

        if (compteOptional.isPresent()) {
            model.addAttribute("compte", compteOptional.get());
            return "comptes/edit"; // Thymeleaf template for editing the account
        } else {
            return "redirect:/comptes?error=Compte+not+found";
        }
    }

    // Handle updating an account
    @PostMapping("/{id}/edit")
    public String updateCompte(@PathVariable("id") String id,
                               @RequestParam("type") String type,
                               @RequestParam("solde") double solde,
                               @RequestParam(required = false) Double decouvert,
                               @RequestParam(required = false) Double taux,
                               @RequestParam Client clientId ,
                               Model model) {

        Compte updatedCompte;

        if ("courant".equalsIgnoreCase(type) && decouvert != null) {
            updatedCompte = new CompteCourant(id, new Date(), solde, decouvert ,  clientId);
        } else if ("epargne".equalsIgnoreCase(type) && taux != null) {
            updatedCompte = new CompteEpargne(id, new Date(), solde, taux ,clientId ) ;
        } else {
            model.addAttribute("error", "Invalid account type or missing fields");
            return "comptes/edit";
        }

        compteMetier.updateCompte(id, updatedCompte);
        return "redirect:/comptes";
    }

    // Handle deleting an account
    @GetMapping("/{id}/delete")
    public String deleteCompte(@PathVariable("id") String id) {
        compteMetier.deleteCompte(id);
        return "redirect:/comptes";
    }


    @GetMapping("/{id}")
    public String getClientById(@PathVariable String id , Model model) {
        Compte compte = compteMetier.getCompteBycodeCompte(id);
        if (compte == null) {
            return "errors/404";
        }
        model.addAttribute("compte", compte);
        return "comptes/details";
    }





@GetMapping("/virement/{comte}")
public String showDepositform(@PathVariable  String comte ,Model model) {

        model.addAttribute("compte" , compteMetier.findById(comte))  ;



        return "comptes/virement" ;


}

    @GetMapping("/retrait/{comte}")
    public String showWithdrawForm(@PathVariable String comte ,Model model) {

        model.addAttribute("compte" ,  compteMetier.findById(comte))  ;



        return "comptes/retrait" ;


    }

    @PostMapping("/virement/{codeComte}")
    public String deposit(@PathVariable String codeComte, @RequestParam double amount) {
        compteMetier.versement(codeComte, amount);
        return "redirect:/comptes/" + codeComte;
    }

    // 7. Withdraw
    @PostMapping("/retrait/{codeComte}")
    public String withdraw(@PathVariable String codeComte, @RequestParam double amount) {
        compteMetier.retrait(codeComte, amount);
        return "redirect:/comptes/" + codeComte;
    }

    @GetMapping("/operations/{code}")
    public String viewCompteOperations(@PathVariable String code, Model model) {
        List<Operation> operations = operationService.findByCompteId(code);
        model.addAttribute("operations", operations);
        return "comptes/listOperations";
    }
    @PostMapping("/comptes/transfer")
    public String transfer(@RequestParam String CompteId, @RequestParam double amount) {
        compteMetier.versement(CompteId, amount);
        return "redirect:/comptes";
    }

    @GetMapping("/comptes/{compteId}")
    public String viewCompte(@PathVariable String compteId, Model model) {
        Compte compte = compteMetier.findById(compteId);
        model.addAttribute("compte", compte);
        return "viewCompte";
    }










































}