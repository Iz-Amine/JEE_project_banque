package com.example.mini_project.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

@GetMapping("/showMyLoginPage")
    public String showLogin(){

    return "fancy-login"   ;
}

@RequestMapping("access-denied")
    public String AceesDenied() {

    return "access-denied"   ;


}






}
