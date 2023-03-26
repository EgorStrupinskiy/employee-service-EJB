package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserRepository {
    List<User> findAll();

    User save(User user);

    User findById(Long id);

    void deleteById(Long id);

    User findByUsername(String username);
}
