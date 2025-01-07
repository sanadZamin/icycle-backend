package com.joprog.icycle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Teams {
    @Id
    private UUID id;
    @Column
    private String teamName;
    @Column
    private String email;
    @Column
    private String mobileNumber;
    @OneToMany
    private List<AppUser> teamAdminList;

}

