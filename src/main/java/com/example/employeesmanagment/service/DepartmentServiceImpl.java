package com.example.employeesmanagment.service;

import com.example.employeesmanagment.persistence.entities.Department;
import com.example.employeesmanagment.persistence.repositories.CrudRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Stateless
public class DepartmentServiceImpl implements DepartmentService {
    @Inject
    private CrudRepository<Department> departmentCrudRepository;

    @Override
    public List<Department> getAll() {
        return departmentCrudRepository.list();
    }

    @Override
    public List<Department> research(String param) {
        return departmentCrudRepository.research(param);
    }

    @Override
    public Department getByCode(String code) {
        return departmentCrudRepository.findByUnique(Department.builder().code(code).build());
    }

    @Transactional
    @Override
    public void save(Department department) {
        department.setCreationTime(LocalDateTime.now());
        department.setUpdateTime(LocalDateTime.now());
        departmentCrudRepository.save(department);
    }

    @Transactional
    @Override
    public void update(Department department) {
        department.setUpdateTime(LocalDateTime.now());
        departmentCrudRepository.update(department);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        departmentCrudRepository.delete(id);
    }

    @Override
    public boolean validateCodeToUpdate(Department department) {
        Integer currentDepartment = department.getId();

        Department departmentFound = this.getByCode(department.getCode());
        if (departmentFound == null){
            return true;
        }
        return Objects.equals(currentDepartment, departmentFound.getId());
    }
}
