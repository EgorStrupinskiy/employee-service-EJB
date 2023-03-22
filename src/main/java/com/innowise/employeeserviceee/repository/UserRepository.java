package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Stateless;

@Stateless
public interface UserRepository{
    User findByUsername(String name);
}
