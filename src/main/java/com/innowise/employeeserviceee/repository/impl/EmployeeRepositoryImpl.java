package com.innowise.employeeserviceee.repository.impl;

import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@NoArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Employee> findAll() {
//        Query query = entityManager.createQuery("from Employee", Employee.class);
//        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
//        return (query.getResultList());
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }

    @Override
    public Employee save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
        return newEmployee;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
