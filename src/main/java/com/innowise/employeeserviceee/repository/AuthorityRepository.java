package com.innowise.employeeserviceee.repository;


import com.innowise.employeeserviceee.entity.Authority;
import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AuthorityRepository {
    List<Authority> findAll();

    Authority save(Authority authority);

    Authority findById(Long id);

    void deleteById(Long id);

    Authority findByName(String username);

}
