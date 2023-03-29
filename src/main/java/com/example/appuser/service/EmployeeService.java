package com.example.appuser.service;

import com.example.appuser.entity.Employee;
import com.example.appuser.entity.Role;
import com.example.appuser.repository.EmployeeRepository;
import com.example.appuser.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Employee createEmployee(Employee employee){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(employee.getPassword()));
        Role employerRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(employerRole);
        employee.setRole(roles);
        employeeRepository.save(employee);
        return employee;
    }

}
