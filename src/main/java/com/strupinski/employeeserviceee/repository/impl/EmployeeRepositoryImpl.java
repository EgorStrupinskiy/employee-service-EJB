package com.strupinski.employeeserviceee.repository.impl;

import com.strupinski.employeeserviceee.entity.Employee;
import com.strupinski.employeeserviceee.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        return Optional.of(entityManager.merge(employee));
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.of(entityManager.find(Employee.class, id));
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
