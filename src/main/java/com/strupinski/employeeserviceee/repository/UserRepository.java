package com.strupinski.employeeserviceee.repository;

import com.strupinski.employeeserviceee.entity.User;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface UserRepository {
    List<User> findAll();

    Optional<User> save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    Optional<User> findByUsername(String username);
}
