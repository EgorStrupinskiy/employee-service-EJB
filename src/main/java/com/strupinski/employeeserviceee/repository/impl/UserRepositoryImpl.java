package com.strupinski.employeeserviceee.repository.impl;

import com.strupinski.employeeserviceee.entity.User;
import com.strupinski.employeeserviceee.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> save(User user) {
        return Optional.of(entityManager.merge(user));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from User " +
                "where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        User user = null;
        try {
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return Optional.of(user);
    }

}
