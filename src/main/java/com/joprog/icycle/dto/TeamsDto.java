package com.joprog.icycle.dto;

import com.joprog.icycle.entity.Roles;

import java.util.UUID;

public record TeamsDto(
        UUID id,
        String teamName,
        Long created_at) {
}
