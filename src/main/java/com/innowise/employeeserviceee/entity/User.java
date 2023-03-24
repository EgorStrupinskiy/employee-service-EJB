package com.innowise.employeeserviceee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Table(name = "users")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    @Column(name = "username")

    private String username;
    @Column(name = "password")

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Authority authority;

    public String getAuthorityName() {
        return this.authority.getName();
    }
}
