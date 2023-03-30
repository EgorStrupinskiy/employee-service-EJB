package com.innowise.employeeserviceee.dto.converter;

import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.entity.User;
import jakarta.ejb.Stateless;

@Stateless
public class UserCardConverter {

    public UserCard toDTO(User user) {
        UserCard userCard = UserCard.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorityId(user.getAuthority().getId())
                .build();
        return userCard;
    }
}
