package com.strupinski.employeeserviceee.repository.impl;


import com.strupinski.employeeserviceee.entity.Authority;
import com.strupinski.employeeserviceee.repository.AuthorityRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;


@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRepositoryImpl implements AuthorityRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    public List<Authority> findAll() {
        return entityManager.createQuery("from Authority", Authority.class).getResultList();
    }

    @Override
    public Optional<Authority> save(Authority authority) {
        return Optional.of(entityManager.merge(authority));
    }

    @Override
    public Optional<Authority> findById(Long id) {
        return Optional.of(entityManager.find(Authority.class, id));
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Authority " +
                "where id =:authorityId");
        query.setParameter("authorityId", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Authority> findByName(String name) {
        return Optional.of((Authority)entityManager.createQuery("SELECT u FROM Authority u WHERE u.name = :name").getSingleResult());
    }
}
