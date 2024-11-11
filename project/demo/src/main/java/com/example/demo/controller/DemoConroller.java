package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoConroller {
@GetMapping("/")
    public String showHome() {
    return "home"   ;
}

    @RequestMapping("/leaders")
    public String showLeaders() {
        return "employees"   ;
    }
    @GetMapping("/systems")
    public String showSystems() {
        return "employeeForm"   ;
    }


}
