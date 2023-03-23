package com.innowise.employeeserviceee.service.impl;


import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.UserRepository;
import com.innowise.employeeserviceee.service.AuthorizationService;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private UserRepository userRepository;

//    @Transactional

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User u = userRepository.findByUsername(username);
//        if (Objects.isNull(u)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", username));
//        }
//        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), true, true, true, true, new HashSet<>());
//    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
