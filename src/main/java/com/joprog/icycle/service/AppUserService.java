package com.joprog.icycle.service;

import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface AppUserService {

    AppUserDto createAppeUser(AppUser appUser);
    Page<AppUserDto> getAllUsers(Pageable pageable);

    Page<AppUserDto> getUsersByName(String name, Pageable pageable);

    AppUserDto getUserById(UUID id);

    void deleteUserById(UUID id);
}
