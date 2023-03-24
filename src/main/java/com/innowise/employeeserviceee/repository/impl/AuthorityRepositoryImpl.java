package com.innowise.employeeserviceee.repository.impl;


import com.innowise.employeeserviceee.entity.Authority;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.AuthorityRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRepositoryImpl implements AuthorityRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    public List<Authority> findAll() {
        Query query = entityManager.createQuery("from Authority", Authority.class);
        return (query.getResultList());
    }

    @Override
    public Authority save(Authority authority) {
        Authority newAuthority = entityManager.merge(authority);
        authority.setId(newAuthority.getId());
        return newAuthority;
    }

    @Override
    public Authority findById(Long id) {
        Authority authority = entityManager.find(Authority.class, id);
        return authority;
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Authority " +
                "where id =:authorityId");
        query.setParameter("authorityId", id);
        query.executeUpdate();
    }

    @Override
    public Authority findByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM Authority u WHERE u.name = :name");
        return (Authority) query.getSingleResult();    }
}
