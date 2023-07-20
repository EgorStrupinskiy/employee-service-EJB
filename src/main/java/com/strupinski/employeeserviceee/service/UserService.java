package com.strupinski.employeeserviceee.service;


import com.strupinski.employeeserviceee.dto.UserCard;
import com.strupinski.employeeserviceee.dto.UserDTO;
import com.strupinski.employeeserviceee.exception.UsernameNotFoundException;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
    UserDTO addUser(UserDTO user) throws UsernameNotFoundException;

    void deleteById(Long id);

    List<UserCard> findAll();

    UserCard findById(Long id);

    UserDTO findByUsername(String username) throws UsernameNotFoundException;

    boolean isAuthenticated(UserDTO user);

    String generateToken(UserDTO user);

}
