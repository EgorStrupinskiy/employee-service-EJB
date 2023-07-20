package com.strupinski.employeeserviceee.repository;


import com.strupinski.employeeserviceee.entity.Authority;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface AuthorityRepository {
    List<Authority> findAll();

    Optional save(Authority authority);

    Optional<Authority> findById(Long id);

    void deleteById(Long id);

    Optional<Authority> findByName(String username);

}
