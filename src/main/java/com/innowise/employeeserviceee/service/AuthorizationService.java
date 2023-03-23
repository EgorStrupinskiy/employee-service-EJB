package com.innowise.employeeserviceee.service;



import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AuthorizationService {
//    UserDetails loadUserByUsername(String username);
    User addUser(User user);

    void deleteById(Long id);
    List<User> findAll();

    User findByUsername(String username);
}
