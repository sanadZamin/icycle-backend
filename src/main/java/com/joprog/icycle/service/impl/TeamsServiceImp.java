package com.joprog.icycle.service.impl;

import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.dto.TeamsDto;
import com.joprog.icycle.entity.AppUser;
import com.joprog.icycle.entity.Roles;
import com.joprog.icycle.mappers.AppUserMappers;
import com.joprog.icycle.reposotiry.AppUserRepository;
import com.joprog.icycle.reposotiry.TeamsRepository;
import com.joprog.icycle.service.AppUserService;
import com.joprog.icycle.service.KeyCloakService;
import com.joprog.icycle.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class TeamsServiceImp implements TeamService {
    private final TeamsRepository teamsRepository;

    @Override
    public TeamsDto createTeam(TeamsDto teamsDto) {

        return null;
    }
}
