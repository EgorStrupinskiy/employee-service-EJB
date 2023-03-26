package com.innowise.employeeserviceee.repository.impl;

import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.repository.DepartmentRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        Query query = entityManager.createQuery("from Department", Department.class);
        return (query.getResultList());
    }

    @Override
    public Department save(Department department) {
        Department newDepartment = entityManager.merge(department);
        department.setId(newDepartment.getId());
        return newDepartment;
    }

    @Override
    public Department findById(Long id) {
        Department department = entityManager.find(Department.class, id);
        return department;
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
        Department department = entityManager.find(Department.class, id);
        return department != null;
    }
}
