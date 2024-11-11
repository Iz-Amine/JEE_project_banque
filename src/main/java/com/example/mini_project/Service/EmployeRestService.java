package com.example.mini_project.Service;

import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employes")
public class EmployeRestService {
//    @Autowired
//    private EmployeMetier employeMetier;
//    @RequestMapping(value="/employes",method= RequestMethod.POST)
//    public Employe saveEmploye(@RequestBody Employe e) {
//        return employeMetier.saveEmploye(e);
//    }
//    @RequestMapping(value="/employes",method=RequestMethod.GET)
//    public List<Employe> listEmployes() {
//        return employeMetier.listEmployes();
//    }

    @Autowired
    private EmployeMetier employeMetier;

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeMetier.saveEmploye(employe);
    }

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeMetier.listEmployes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Optional<Employe> employe = employeMetier.getEmployeById(id);
        return employe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        Employe updatedEmploye = employeMetier.updateEmploye(id, employe);
        if (updatedEmploye != null) {
            return ResponseEntity.ok(updatedEmploye);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        employeMetier.deleteEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
