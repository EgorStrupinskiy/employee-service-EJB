package com.innowise.employeeserviceee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "authority")
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority")

    private String name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<User> users;
}
