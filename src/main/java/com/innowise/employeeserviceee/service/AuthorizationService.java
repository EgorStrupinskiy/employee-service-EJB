package com.innowise.employeeserviceee.service;



import com.innowise.employeeserviceee.entity.User;

import java.util.List;

public interface AuthorizationService {
//    UserDetails loadUserByUsername(String username);
    User addUser(User user);

    void deleteById(Long id);
    List<User> findAll();

    User findByUsername(String username);
}
