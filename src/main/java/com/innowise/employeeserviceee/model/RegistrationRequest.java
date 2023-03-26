package com.innowise.employeeserviceee.model;


import com.innowise.employeeserviceee.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    private String username;

    private String password;

    private Long authorityId;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .password(password)
                .authorityId(authorityId)
                .build();
    }
}