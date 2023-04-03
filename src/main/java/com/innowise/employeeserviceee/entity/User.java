package com.innowise.employeeserviceee.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @Size(min = 5, max = 45, message = "Username must be between 5 and 45 characters")
    private String username;
    @Column(name = "password")
    @Size(min = 5, max = 255, message = "Password must be between 5 and 255 characters")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @NotNull(message = "Authority can`t be null")
    private Authority authority;


    public String getAuthorityName() {
        return this.authority.getName();
    }
}
