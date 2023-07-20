package com.strupinski.employeeserviceee.dto.converter;

import com.strupinski.employeeserviceee.dto.UserCard;
import com.strupinski.employeeserviceee.entity.User;
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
