package com.joprog.icycle.dto;

import com.joprog.icycle.entity.Roles;

import java.util.UUID;

public record AppUserDto(UUID id, String firstName, String lastName,String email, Roles roles) {
}
