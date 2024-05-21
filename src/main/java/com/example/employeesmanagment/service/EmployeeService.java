package com.example.employeesmanagment.service;

import com.example.employeesmanagment.persistence.entities.Employee;
import jakarta.ejb.Local;

import java.util.List;
@Local
public interface EmployeeService {
    List<Employee> getAll();
    List<Employee> research(String param);
    Employee getByDocumentNumber(Long documentNumber);
    void save(Employee employee);
    void update(Employee employee);
    void delete(Integer id);
    boolean validateDocumentNumberToUpdate(Employee employee);
}
