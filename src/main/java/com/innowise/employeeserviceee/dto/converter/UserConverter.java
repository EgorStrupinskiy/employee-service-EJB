package com.innowise.employeeserviceee.dto.converter;


import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.Authority;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.repository.AuthorityRepository;
import com.innowise.employeeserviceee.repository.impl.AuthorityRepositoryImpl;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserConverter {

    @EJB
    private AuthorityRepository authorityRepository;

    public User toEntity(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        Optional.ofNullable(userDTO.getAuthorityId())
                .ifPresent(id -> {
                    Authority au = authorityRepository.findById(userDTO.getAuthorityId());
                    user.setAuthority(au);
                });

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
                .ifPresent(authority -> userDTO.setAuthorityId(authority.getId()));

        return userDTO;
    }
}
