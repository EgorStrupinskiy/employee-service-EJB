package com.innowise.employeeserviceee.dto;

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

    private List<Long> usersIds;

    public void addUserId(Long id) {
        if (usersIds == null) {
            usersIds = new ArrayList<>();
        }
        usersIds.add(id);
    }
}