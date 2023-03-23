package com.innowise.employeeserviceee.dto.converter;


import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.AuthorityRepository;
import jakarta.ejb.Singleton;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Singleton
@NoArgsConstructor
@AllArgsConstructor
public class UserConverter {

    private AuthorityRepository authorityRepository;

    public User toEntity(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();

//        Optional.ofNullable(userDTO.getAuthority())
//                .ifPresent(id -> {
//                    Authority au = authorityRepository.findByName(userDTO.getAuthority());
//                    user.setAuthority(au);
//                });

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        System.out.println(userDTO);
        Optional.ofNullable(user.getAuthority())
                .ifPresent(authority -> userDTO.setAuthority(authority.getName()));

        return userDTO;
    }
}
