package com.example.employeesmanagment.persistence.repositories;

import java.util.List;

public interface CrudRepository<T>{
    List<T> list();
    long count();
    T findById(Integer id);
    T findByUnique(T attribute);
    List<T> research(String param);
    void save(T t);
    void update(T t);
    void delete(Integer id);
}
