package com.example.mini_project.Controller;

import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Entity.Groupe;
import com.example.mini_project.Metier.EmployeMetier;
import com.example.mini_project.Metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groupes")
public class GroupeController {

    @Autowired
    private GroupeMetier groupeMetier;
    @Autowired
    private EmployeMetier employeMetier;

    // Show all groupes in a list view
    @GetMapping
    public String getAllGroupes(Model model) {
        List<Groupe> groupes = groupeMetier.getAllGroupes();  // Fetch only active groups
        model.addAttribute("groupes", groupes);
        return "groupes/list"; // Thymeleaf template (list.html)
    }

    // Soft delete a groupe by ID
    @GetMapping("/{id}/delete")
    public String deleteGroupe(@PathVariable Long id) {
        groupeMetier.deleteGroupe(id);  // Soft delete by setting isDeleted to true
        return "redirect:/groupes"; // Redirect to the list view after deletion
    }

    // Show a form to create a new groupe
    @GetMapping("/new")
    public String showCreateGroupeForm(Model model) {
        model.addAttribute("groupe", new Groupe());
        return "groupes/create"; // Thymeleaf template (create.html)
    }

    // Process the form to create a new groupe
    @PostMapping("/new")
    public String createGroupe(@ModelAttribute("groupe") Groupe groupe) {
        groupeMetier.saveGroupe(groupe);
        return "redirect:/groupes"; // Redirect to the list view after creation
    }

    // Show a form to update an existing groupe
    @GetMapping("/{id}/edit")
    public String showUpdateGroupeForm(@PathVariable Long id, Model model) {
        Groupe groupe = groupeMetier.getGroupeById(id);
        if (groupe == null) {
            return "errors/404"; // Thymeleaf template for error page
        }
        model.addAttribute("groupe", groupe);
        return "groupes/edit"; // Thymeleaf template (edit.html)
    }

    // Process the form to update an existing groupe
    @PostMapping("/{id}/edit")
    public String updateGroupe(@PathVariable Long id, @ModelAttribute("groupe") Groupe groupe) {
        groupeMetier.updateGroupe(id, groupe);
        return "redirect:/groupes"; // Redirect to the list view after updating
    }

    // Delete a groupe by ID
//    @GetMapping("/{id}/delete")
//    public String deleteGroupe(@PathVariable Long id) {
//        groupeMetier.deleteGroupe(id);
//        return "redirect:/groupes"; // Redirect to the list view after deletion
//    }




    // Show details of a specific groupe
    @GetMapping("/{id}")
    public String getGroupeById(@PathVariable Long id, Model model) {
        Groupe groupe = groupeMetier.getGroupeById(id);
        if (groupe == null) {
            return "errors/404"; // Thymeleaf template for error page
        }
        model.addAttribute("groupe", groupe);
        return "groupes/details"; // Thymeleaf template (details.html)
    }

    // Show form to add an employee to a specific group
    @GetMapping("/{id}/addEmployee")
    public String showAddEmployeeForm(@PathVariable Long id, Model model) {
        Groupe groupe = groupeMetier.getGroupeById(id);
        if (groupe == null) {
            return "errors/404";
        }

        // Retrieve employees not currently in this group
        List<Employe> availableEmployees = employeMetier.listEmployes().stream()
                .filter(e -> !groupe.getEmploye().contains(e))
                .collect(Collectors.toList());

        model.addAttribute("groupe", groupe);
        model.addAttribute("employees", availableEmployees);
        model.addAttribute("selectedEmployee", new Employe()); // To hold selected employee ID
        return "groupes/addEmployee"; // Thymeleaf template for adding employee
    }

    // Handle form submission to add an employee to the group
    @PostMapping("/{id}/addEmployee")
    public String addEmployeeToGroup(@PathVariable Long id, @RequestParam Long employeeId, Model model) {
        Groupe groupe = groupeMetier.getGroupeById(id);
        Employe employe = employeMetier.getEmployeById(employeeId).orElse(null);

        if (groupe != null && employe != null) {
            groupe.addEmploye(employe);
            groupeMetier.saveGroupe(groupe);
        }

//        // Add attributes to the model to display the selected employee's details
//        model.addAttribute("groupe", groupe);
//        model.addAttribute("employees", employeMetier.listEmployes().stream()
//                .filter(e -> !groupe.getEmploye().contains(e))
//                .collect(Collectors.toList()));
//        model.addAttribute("selectedEmployee", employe); // Pass selected employee for display

//        return "groupes/addEmployee";

        return "redirect:/groupes/" + id; // Redirect back to group details
    }


    @GetMapping("/{groupId}/removeEmployee")
    public String removeEmployeeFromGroup(@PathVariable Long groupId, @RequestParam Long employeeId) {
        Groupe groupe = groupeMetier.getGroupeById(groupId);
        Employe employe = employeMetier.getEmployeById(employeeId).orElse(null);

        if (groupe != null && employe != null) {
            // Remove the employee from the group's collection
            groupe.getEmploye().remove(employe);
            // Also remove the group from the employee's collection to maintain consistency
            employe.getGroupes().remove(groupe);

            // Save both entities to persist the changes
            groupeMetier.saveGroupe(groupe);
            employeMetier.saveEmploye(employe);
        }

        return "redirect:/groupes/" + groupId; // Redirect back to the group's details page
    }

}