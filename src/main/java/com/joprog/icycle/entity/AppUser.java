package com.joprog.icycle.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "app_user_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AppUser {
    @Id
    private UUID id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String mobileNumber;
    @Column
    private Roles role;

}
