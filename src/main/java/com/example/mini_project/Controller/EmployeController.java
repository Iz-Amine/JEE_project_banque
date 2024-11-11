package com.example.mini_project.Controller;


import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private EmployeMetier employeMetier;

    // Show all employees
    @GetMapping
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeMetier.listEmployes();
        model.addAttribute("employes", employes);
        return "employes/list"; // Thymeleaf template (list.html) under src/main/resources/templates/employes/
    }

    // Show form to create a new employee
    @GetMapping("/new")
    public String showCreateEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        return "employes/create"; // Thymeleaf template (create.html)
    }

    // Process form to create a new employee
    @PostMapping("/new")
    public String createEmploye(@ModelAttribute("employe") Employe employe) {
        employeMetier.saveEmploye(employe);
        return "redirect:/employes"; // Redirect to the list view after creation
    }

    // Show details of a specific employee
    @GetMapping("/{id}")
    public String getEmployeById(@PathVariable Long id, Model model) {
        Employe employe = employeMetier.getEmployeById(id).orElse(null);
        if (employe == null) {
            return "errors/404"; // Handle not found case
        }
        model.addAttribute("employe", employe);
        return "employes/details"; // Thymeleaf template (details.html)
    }

    // Show form to edit an existing employee
    @GetMapping("/{id}/edit")
    public String showEditEmployeForm(@PathVariable Long id, Model model) {
        Employe employe = employeMetier.getEmployeById(id).orElse(null);
        if (employe == null) {
            return "errors/404";
        }
        model.addAttribute("employe", employe);

        // // to do
        model.addAttribute("supervisors", employeMetier.listEmployes());
        return "employes/edit"; // Thymeleaf template (edit.html)
    }

    // Process form to update an existing employee
    @PostMapping("/{id}/edit")
    public String updateEmploye(@PathVariable Long id, @ModelAttribute("employe") Employe employe) {
        employeMetier.updateEmploye(id, employe);
        return "redirect:/employes"; // Redirect to the list view after updating
    }

    // Delete an employee by ID
    @GetMapping("/{id}/delete")
    public String deleteEmploye(@PathVariable Long id) {
        employeMetier.deleteEmploye(id);
        return "redirect:/employes"; // Redirect to the list view after deletion
    }
}
