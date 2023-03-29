package com.example.appuser.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Employee {
    @Id
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "First Name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty.")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty(message = "Password cannot be empty.")
    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_roles", joinColumns = {
            @JoinColumn(name = "employee_email",
                        referencedColumnName = "email")},
                        inverseJoinColumns = {
                        @JoinColumn(name = "role_name",
                                    referencedColumnName = "name")})
    private List<Role> role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Employee(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Employee() {

    }
}
