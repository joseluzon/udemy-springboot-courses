package com.github.joseluzon.udemy.springboot.automationtesting.microservices.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Employee;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.AddressRepository;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private AddressRepository addressRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(final int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(final Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(final int id, final Employee employee) {
        final var currentEmployee = findById(id);
        currentEmployee.setName(employee.getName());
        currentEmployee.setEmail(employee.getEmail());
        currentEmployee.setPhone(employee.getPhone());
        currentEmployee.setAddress(employee.getAddress());
        return employeeRepository.save(currentEmployee);
    }

    public void deleteEmployee(final int id) {
        findById(id);
        employeeRepository.deleteById(id);
    }
}
