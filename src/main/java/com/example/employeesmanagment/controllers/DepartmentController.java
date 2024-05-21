package com.example.employeesmanagment.controllers;

import com.example.employeesmanagment.infraestructure.CustomQualifier;
import com.example.employeesmanagment.persistence.entities.Department;
import com.example.employeesmanagment.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.el.MethodExpression;
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
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Named
@ViewScoped
public class DepartmentController implements Serializable {

    @Inject
    private DepartmentService departmentService;
    @Inject
    @CustomQualifier
    private FacesContext facesContext;

    private List<Department> departments;
    private Department department;
    private String researchParam;

    @PostConstruct
    public void init() {
        this.loadDepartments();
    }

    public void loadDepartments() {
        this.departments = departmentService.getAll();
        this.department = new Department();
        PrimeFaces.current().executeScript("PF('dialogForm').hide();");
    }
    public void save() {
        if (Objects.nonNull(departmentService.getByCode(department.getCode()))) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El código debe ser único", null));
            return;
        }
        departmentService.save(department);
        facesContext.addMessage(null, new FacesMessage("Se guardó el departamento con éxito, id: "
                + department.getId(), null));
    }

    public void update() {
        boolean validateByCode = departmentService.validateCodeToUpdate(department);
        if (!validateByCode) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El código debe ser unico", null));
            return;
        }
        departmentService.update(department);
        facesContext.addMessage(null, new FacesMessage("Se actualizó el departamento con éxito", null));
    }

    public void handleSaveOrUpdate() {
        if (department.getId() == null) {
            this.save();
        } else {
            this.update();
        }
        this.loadDepartments();
    }

    public void delete(Department department) {
        departmentService.delete(department.getId());
        facesContext.addMessage(null, new FacesMessage("Se eliminó el departamento con éxito", null));
        this.loadDepartments();
    }

    public Department getDepartment() {
        if (department == null) {
            department = new Department();
        }
        return department;
    }

    public void preSave() {
        this.setDepartment(new Department());
    }

    public void preUpdate(Department department) {
        this.setDepartment(department);
    }

    public void research(){
        this.departments = departmentService.research(researchParam);
    }

}
