package com.innowise.employeeserviceee.repository.impl;

import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
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
    //todo: fix findByUsername if it`s possible
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        User user = null;
        try {
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch (NoResultException ignored) {

        }

        return user;
    }

}
