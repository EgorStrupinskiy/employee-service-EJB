package com.innowise.employeeserviceee.model;


import com.innowise.employeeserviceee.dto.UserDTO;
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

    private String authority;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .password(password)
                .authority(authority)
                .build();
    }
}