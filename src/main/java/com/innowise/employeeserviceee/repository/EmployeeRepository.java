package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.Employee;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
public class EmployeeRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    public List<Employee> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return (query.getResultList());
//        TypedQuery<Employee> namedQuery = entityManager.createNamedQuery("Employee.getAll", Employee.class);
//        return namedQuery.getResultList();
    }

    public Employee save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
        return newEmployee;
    }

    public Employee findById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
