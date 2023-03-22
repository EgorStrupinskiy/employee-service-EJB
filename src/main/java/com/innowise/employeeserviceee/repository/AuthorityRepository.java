package com.innowise.employeeserviceee.repository;


import com.innowise.employeeserviceee.entity.Authority;
import jakarta.ejb.Stateless;

@Stateless
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}
