package com.innowise.employeeserviceee.service;



import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
//    UserDetails loadUserByUsername(String username);
    UserDTO addUser(UserDTO user);

    void deleteById(Long id);
    List<UserDTO> findAll();

    UserDTO findByUsername(String username);
}
