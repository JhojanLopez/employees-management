package com.example.employeesmanagment.service;

import com.example.employeesmanagment.persistence.entities.Department;
import com.example.employeesmanagment.persistence.entities.Employee;
import com.example.employeesmanagment.persistence.repositories.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Stateless
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private CrudRepository<Employee> employeeCrudRepository;

    @Override
    public List<Employee> getAll() {
        return employeeCrudRepository.list();
    }

    @Override
    public List<Employee> research(String param) {
        return employeeCrudRepository.research(param);
    }

    @Override
    public Employee getByDocumentNumber(Long documentNumber) {
        return employeeCrudRepository.findByUnique(Employee.builder().documentNumber(documentNumber).build());
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employee.setCreationTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employeeCrudRepository.save(employee);
    }

    @Transactional
    @Override
    public void update(Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employeeCrudRepository.update(employee);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        employeeCrudRepository.delete(id);
    }

    @Override
    public boolean validateDocumentNumberToUpdate(Employee employee) {
        Integer currentDepartment = employee.getId();

        Employee employeeFound = this.getByDocumentNumber(employee.getDocumentNumber());
        if (employeeFound == null) {
            return true;
        }
        return Objects.equals(currentDepartment, employeeFound.getId());
    }
}
