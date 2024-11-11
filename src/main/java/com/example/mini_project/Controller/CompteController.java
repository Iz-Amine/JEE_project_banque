package com.example.mini_project.Controller;

import com.example.mini_project.Entity.Compte;
import com.example.mini_project.Entity.CompteCourant;
import com.example.mini_project.Entity.CompteEpargne;
import com.example.mini_project.Entity.Operation;
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
        return "comptes/create"; // Thymeleaf template for creating a new account
    }

    @PostMapping
    public String createCompte(@RequestParam("type") String type,
                               @RequestParam("codeCompte") String codeCompte,
                               @RequestParam("solde") double solde,
                               @RequestParam(required = false) Double decouvert,
                               @RequestParam(required = false) Double taux,
                               @RequestParam Long clientId,
                               Model model) {

        Compte compte;
        Date dateCreation = new Date(); // Set the current date

        // Check type and required fields
        if ("courant".equalsIgnoreCase(type) && decouvert != null) {
            compte = new CompteCourant(codeCompte, dateCreation, solde, decouvert ,clientId );
        } else if ("epargne".equalsIgnoreCase(type) && taux != null) {
            compte = new CompteEpargne(codeCompte, dateCreation, solde, taux  ,clientId);
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
                               @RequestParam Long clientId ,
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



    @PostMapping("/comptes/{codeComte}/deposit")
    public String deposit(@PathVariable String codeComte, @RequestParam double amount) {
        compteMetier.versement(codeComte, amount);
        return "redirect:/bank/comptes/" + codeComte;
    }

    // 7. Withdraw
    @PostMapping("/comptes/{codeComte}/withdraw")
    public String withdraw(@PathVariable String codeComte, @RequestParam double amount) {
        compteMetier.retrait(codeComte, amount);
        return "redirect:/bank/comptes/" + codeComte;
    }

    @GetMapping("/comptes/{code}/operations")
    public String viewCompteOperations(@PathVariable String code, Model model) {
        List<Operation> operations = operationService.findByCompteId(code);
        model.addAttribute("operations", operations);
        return "listOperations";
    }
    @PostMapping("/comptes/transfer")
    public String transfer(@RequestParam String CompteId, @RequestParam double amount) {
        compteMetier.versement(CompteId, amount);
        return "redirect:/bank/comptes";
    }

    @GetMapping("/comptes/{compteId}")
    public String viewCompte(@PathVariable String compteId, Model model) {
        Compte compte = compteMetier.findById(compteId);
        model.addAttribute("compte", compte);
        return "viewCompte";
    }










































}