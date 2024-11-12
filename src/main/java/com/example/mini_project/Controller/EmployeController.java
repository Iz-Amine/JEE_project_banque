package com.example.mini_project.Controller;


import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employes")
public class EmployeController {

    @Autowired
    private EmployeMetier employeMetier;

    @GetMapping
    public String getAllEmployes(Model model) {
        List<Employe> employes = employeMetier.listEmployes();  // Fetch only active employees

        // Calculate active group count for each employee
        Map<Long, Long> activeGroupCounts = employes.stream()
                .collect(Collectors.toMap(
                        Employe::getCodeEmploye,
                        employe -> employe.getGroupes().stream()
                                .filter(group -> !group.isDeleted())
                                .count()
                ));
        // Map each employee's ID to their active supervisor's name, or "N/A" if supervisor is inactive or null
        Map<Long, String> activeSupervisors = employes.stream()
                .collect(Collectors.toMap(
                        Employe::getCodeEmploye,
                        employe -> (employe.getEmployeSup() != null && !employe.getEmployeSup().isDeleted())
                                ? employe.getEmployeSup().getNomEmploye()
                                : "N/A"
                ));

        model.addAttribute("employes", employes);
        model.addAttribute("activeGroupCounts", activeGroupCounts);  // Pass group counts to the view
        model.addAttribute("activeSupervisors", activeSupervisors);  // Pass active supervisor info to the view
        return "employes/list";
    }


    // Soft delete an employee by ID
    @GetMapping("/{id}/delete")
    public String deleteEmploye(@PathVariable Long id) {
        employeMetier.deleteEmploye(id);  // Soft delete by setting isDeleted to true
        return "redirect:/employes"; // Redirect to the list view after deletion
    }
    // Show form to create a new employee
    @GetMapping("/new")
    public String showCreateEmployeForm(Model model) {
        model.addAttribute("employe", new Employe());
        List<Employe> supervisors = employeMetier.listEmployes();
        if (supervisors == null) {
            supervisors = new ArrayList<>(); // Initialize an empty list if null
        }
        model.addAttribute("supervisors", supervisors);
        return "employes/create";
    }


    // Process form to create a new employee
    @PostMapping("/new")
    public String createEmploye(@ModelAttribute("employe") Employe employe,
                                @RequestParam(name = "selectedSupervisor", required = false) Long selectedSupervisorId) {
        if (selectedSupervisorId != null) {
            Employe supervisor = employeMetier.getEmployeById(selectedSupervisorId).orElse(null);
            employe.setEmployeSup(supervisor); // Set the selected supervisor
        }
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

        List<Employe> supervisors = employeMetier.listEmployes();
        if (supervisors == null) {
            supervisors = new ArrayList<>(); // Initialize an empty list if null
        }
        model.addAttribute("supervisors", supervisors);
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
    public String updateEmploye(@PathVariable Long id, @ModelAttribute("employe") Employe employe,
                                @RequestParam(name = "selectedSupervisor", required = false) Long selectedSupervisorId) {
        if (selectedSupervisorId != null) {
            Employe supervisor = employeMetier.getEmployeById(selectedSupervisorId).orElse(null);
            employe.setEmployeSup(supervisor); // Set the selected supervisor
        }
        employeMetier.updateEmploye(id, employe);
        return "redirect:/employes"; // Redirect to the list view after updating
    }

    // Delete an employee by ID
//    @GetMapping("/{id}/delete")
//    public String deleteEmploye(@PathVariable Long id) {
//        employeMetier.deleteEmploye(id);
//        return "redirect:/employes"; // Redirect to the list view after deletion
//    }
}
