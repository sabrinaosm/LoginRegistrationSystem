package com.example.appuser.controller;

import com.example.appuser.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.appuser.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


//    Index
    @GetMapping("/")
    public String homePage() {
        return "views/index";
    }

//    Login

    @GetMapping("/login")
    public String login() {
        return "views/loginForm";
    }

//    Registration

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "views/registerForm";
        }

        employeeService.createEmployee(employee);
        return "redirect:/login";
    }

//    Logout
    @GetMapping("/logout")
    public String logoutPage(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}

