package com.example.employeesmanagment.persistence.repositories;

import com.example.employeesmanagment.persistence.entities.Department;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@RequestScoped
public class DepartmentRepository implements CrudRepository<Department> {
    @Inject
    private EntityManager em;

    @Override
    public List<Department> list() {
        return em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    @Override
    public long count() {
        return (long) em.createQuery("SELECT count(d) FROM Department d").getSingleResult();
    }

    @Override
    public Department findById(Integer id) {
        return em.find(Department.class, id);
    }

    @Override
    public Department findByUnique(Department department) {
        try {
            TypedQuery<Department> query = em.createQuery(
                    "SELECT d FROM Department d WHERE d.code = :code", Department.class);
            query.setParameter("code", department.getCode());
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Department> research(String param) {
        return em.createQuery("SELECT d FROM Department d WHERE LOWER(d.code) LIKE LOWER(:param) OR LOWER(d.name) LIKE LOWER(:param)")
                .setParameter("param", "%" + param + "%")
                .getResultList();
    }

    @Override
    public void save(Department department) {
        em.persist(department);
    }

    @Override
    public void update(Department department) {
        em.merge(department);
    }

    @Override
    public void delete(Integer id) {
        em.remove(this.findById(id));
    }
}
