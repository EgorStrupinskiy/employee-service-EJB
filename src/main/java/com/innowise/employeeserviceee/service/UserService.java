package com.innowise.employeeserviceee.service;



import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.exception.UsernameNotFoundException;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
//    UserDetails loadUserByUsername(String username);
    UserDTO addUser(UserDTO user) throws UsernameNotFoundException;

    void deleteById(Long id);
    List<UserCard> findAll();

    UserCard findById(Long id);

    UserDTO findByUsername(String username) throws UsernameNotFoundException;

    boolean isAuthenticated(UserDTO user);

    String generateToken(UserDTO user);

}
