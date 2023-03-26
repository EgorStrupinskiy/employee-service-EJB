package com.innowise.employeeserviceee.repository.impl;

import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> findAll() {
        Query query = entityManager.createQuery("from User", User.class);
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Override
    public User save(User user) {
        User newUser = entityManager.merge(user);
//        newUser.setId(user.getId());
        return newUser;
    }


    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from User " +
                "where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
        return (User) query.getSingleResult();
    }
}
