package com.innowise.employeeserviceee.repository;


import com.innowise.employeeserviceee.entity.Authority;
import jakarta.ejb.EJB;
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
public class AuthorityRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    public List<Authority> findAll() {
        Query query = entityManager.createQuery("from Authority", Authority.class);
        return (query.getResultList());
    }

    public Authority save(Authority authority) {
        Authority newAuthority = entityManager.merge(authority);
        authority.setId(newAuthority.getId());
        return newAuthority;
    }

    public Authority findById(Long id) {
        Authority authority = entityManager.find(Authority.class, id);
        return authority;
    }

    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Authority " +
                "where id =:authorityId");
        query.setParameter("authorityId", id);
        query.executeUpdate();
    }
}
