package com.joprog.icycle.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
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
    @ManyToMany
    private List<Teams> team;
    @Column(nullable = false, updatable = false)
    private Long created_at;



}
