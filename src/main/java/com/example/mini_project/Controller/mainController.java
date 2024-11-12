package com.example.mini_project.Controller;

import com.example.mini_project.Metier.ClientMetier;
import com.example.mini_project.Metier.CompteMetier;
import com.example.mini_project.Metier.EmployeMetier;
import com.example.mini_project.Metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @Autowired
    private CompteMetier compteMetier;

    @Autowired
    private ClientMetier clientMetier;

    @Autowired
    private GroupeMetier groupeMetier;
    @Autowired
    private EmployeMetier employeMetier;


    @GetMapping("/")
    public String dashboard(Model model) {
        long groupCount = groupeMetier.getAllGroupes().size();
        long employeeCount = employeMetier.listEmployes().size();
        long clientCount = clientMetier.listClient().size(); // Assuming you have a clientMetier
        long accountCount = compteMetier.listComptes().size(); // Assuming you have an accountMetier

        model.addAttribute("groupCount", groupCount);
        model.addAttribute("employeeCount", employeeCount);
        model.addAttribute("clientCount", clientCount);
        model.addAttribute("accountCount", accountCount);

        return "index";
    }

}
