package com.innowise.employeeserviceee.service;



import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.exception.UsernameNotFoundException;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
//    UserDetails loadUserByUsername(String username);
    UserDTO addUser(UserDTO user);

    void deleteById(Long id);
    List<UserCard> findAll();

    UserCard findById(Long id);

    UserDTO findByUsername(String username) throws UsernameNotFoundException;

    boolean checkCredentials(UserDTO user);

    String generateToken(UserDTO user);

}
