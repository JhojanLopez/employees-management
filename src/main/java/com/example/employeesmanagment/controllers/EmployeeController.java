package com.example.employeesmanagment.controllers;

import com.example.employeesmanagment.infraestructure.CustomQualifier;
import com.example.employeesmanagment.persistence.entities.Department;
import com.example.employeesmanagment.persistence.entities.DocumentType;
import com.example.employeesmanagment.persistence.entities.Employee;
import com.example.employeesmanagment.service.DepartmentService;
import com.example.employeesmanagment.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Named
@ViewScoped
public class EmployeeController implements Serializable {
    @Inject
    private EmployeeService employeeService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    @CustomQualifier
    private FacesContext facesContext;
    private Employee employee;
    private Department department;
    private List<Employee> employees;
    private String researchParam;

    @PostConstruct
    public void init() {
        this.loadEmployees();
    }

    public void loadEmployees() {
        this.employees = employeeService.getAll();
        this.employee = new Employee();
        this.department = new Department();
        this.employee.setDepartment(department);
        PrimeFaces.current().executeScript("PF('dialogForm').hide();");
    }

    public List<DocumentType> documentTypes() {
        return Arrays.asList(DocumentType.values());
    }

    public void save() {
        if (Objects.nonNull(employeeService.getByDocumentNumber(employee.getDocumentNumber()))) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El numero de documento debe ser único", null));
            return;
        }

        Department departmentFound = departmentService.getByCode(department.getCode());
        if (Objects.isNull(departmentFound)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El código del departamento no existe, por favor ingresa uno existente", null));
            return;
        }
        employee.setDepartment(departmentFound);
        employeeService.save(employee);
        facesContext.addMessage(null, new FacesMessage("Se guardó el departamento con éxito, id: "
                + employee.getId(), null));
    }

    public void update() {
        boolean validateByCode = employeeService.validateDocumentNumberToUpdate(employee);
        if (!validateByCode) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El numero de documento debe ser único", null));
            return;
        }
        Department departmentFound = departmentService.getByCode(department.getCode());
        if (Objects.isNull(departmentFound)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El código del departamento no existe, por favor ingresa uno existente", null));
            return;
        }
        employee.setDepartment(departmentFound);
        employeeService.update(employee);
        facesContext.addMessage(null, new FacesMessage("Se actualizó el empleado con éxito", null));
    }

    public void handleSaveOrUpdate() {
        if (employee.getId() == null) {
            this.save();
        } else {
            this.update();
        }
        this.loadEmployees();
    }

    public void delete(Employee employee) {
        employeeService.delete(employee.getId());
        facesContext.addMessage(null, new FacesMessage("Se eliminó el empleado con éxito", null));
        this.loadEmployees();
    }

    public Employee getEmployee() {
        if (employee == null) {
            this.employee = new Employee();
            this.department = new Department();
            this.employee.setDepartment(department);
        }
        return employee;
    }

    public void preSave() {
        this.setEmployee(new Employee());
        this.setDepartment(new Department());
    }

    public void preUpdate(Employee employee) {

        if (Objects.nonNull(employee.getDepartment())) {
            this.department = Department.builder()
                    .code(employee.getDepartment().getCode())
                    .build();
        }
        this.setEmployee(employee);
    }

    public void research(){
        this.employees = employeeService.research(researchParam);
    }
}
