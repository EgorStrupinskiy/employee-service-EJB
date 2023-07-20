package com.strupinski.employeeserviceee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorityDTO {

    private Long id;

    private String name;

    private List<UserDTO> users;

    public void addUser(UserDTO user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}

