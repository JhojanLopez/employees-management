package com.example.employeesmanagment.service;

import com.example.employeesmanagment.persistence.entities.Department;
import jakarta.ejb.Local;

import java.util.List;
@Local
public interface DepartmentService {
    List<Department> getAll();
    List<Department> research(String param);
    Department getByCode(String code);
    void save(Department department);
    void update(Department department);
    void delete(Integer id);
    boolean validateCodeToUpdate(Department department);
}
