package com.strupinski.employeeserviceee.repository.impl;

import com.strupinski.employeeserviceee.entity.Department;
import com.strupinski.employeeserviceee.repository.DepartmentRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("from Department", Department.class).getResultList();
    }

    @Override
    public Optional<Department> save(Department department) {
        return Optional.of(entityManager.merge(department));
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.of(entityManager.find(Department.class, id));
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Department " +
                "where id =:departmentId");
        query.setParameter("departmentId", id);
        query.executeUpdate();
    }

    @Override
    public boolean existsById(Long id) {
        return entityManager.find(Department.class, id) != null;
    }
}
