package com.innowise.employeeserviceee.service.impl;//package com.innowise.employeeserviceee.service.impl;
//
//
//import com.innowise.employeeserviceee.entity.User;
//import com.innowise.employeeserviceee.repository.UserRepository;
//import com.innowise.employeeserviceee.repository.impl.UserRepositoryImpl;
//import com.innowise.employeeserviceee.service.AuthorizationService;
//import jakarta.ejb.EJB;
//import jakarta.ejb.Stateless;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Stateless
//@NoArgsConstructor
//@AllArgsConstructor
//public class AuthorizationServiceImpl implements AuthorizationService {
//    @EJB
//    private UserRepository userRepository;
//
////    @Transactional
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User u = userRepository.findByUsername(username);
////        if (Objects.isNull(u)) {
////            throw new UsernameNotFoundException(String.format("User %s is not found", username));
////        }
////        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), true, true, true, true, new HashSet<>());
////    }
//
//    @Override
//    public User addUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Transactional
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return null;
//    }
//}


import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.dto.converter.UserConverter;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.UserRepository;
import com.innowise.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @EJB
    private UserRepository userRepository;
    @EJB
    private UserConverter converter;
//    @EJB
//    private UserMapper mapper;

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
    public UserDTO addUser(UserDTO userDTO) {
        User entityDTO = converter.toEntity(userDTO);
        User returnedEntity = userRepository.save(entityDTO);
        UserDTO convertedEntity = converter.toDTO(returnedEntity);
        return convertedEntity;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(String.format("User %s is not found", username));
//        }

        return converter.toDTO(user);
    }

}
