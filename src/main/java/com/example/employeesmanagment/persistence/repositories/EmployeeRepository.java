package com.example.employeesmanagment.persistence.repositories;

import com.example.employeesmanagment.persistence.entities.Employee;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@RequestScoped
public class EmployeeRepository implements CrudRepository<Employee> {
    @Inject
    private EntityManager em;

    @Override
    public List<Employee> list() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Employee findById(Integer id) {
        return em.find(Employee.class, id);
    }

    @Override
    public Employee findByUnique(Employee employee) {
        try {
            TypedQuery<Employee> query = em.createQuery(
                    "SELECT e FROM Employee e WHERE e.documentNumber = :documentNumber", Employee.class);
            query.setParameter("documentNumber", employee.getDocumentNumber());
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Employee> research(String param) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.documentNumber LIKE :param OR LOWER(e.names) LIKE LOWER(:param)")
                .setParameter("param", "%" + param + "%")
                .getResultList();
    }

    @Override
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    public void delete(Integer id) {
        em.remove(this.findById(id));
    }
}
