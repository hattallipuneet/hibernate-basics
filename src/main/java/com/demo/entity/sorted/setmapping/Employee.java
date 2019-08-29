package com.demo.entity.sorted.setmapping;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ElementCollection
    @CollectionTable(name = "salaries")
    @OrderBy("salary DESC")
    @Column(name = "salary")
    private Set<Integer> salaries = new LinkedHashSet<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Integer> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Integer> salaries) {
        this.salaries = salaries;
    }
}
