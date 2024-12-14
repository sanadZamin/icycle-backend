package com.joprog.icycle.service.impl;

import com.joprog.icycle.Exceptions.EmailValidationException;
import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import com.joprog.icycle.entity.Roles;
import com.joprog.icycle.mappers.AppUserMappers;
import com.joprog.icycle.reposotiry.AppUserRepository;
import com.joprog.icycle.service.AppUserService;
import com.joprog.icycle.service.KeyCloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImp implements AppUserService {


    private final AppUserRepository appUserRepository;
    private final AppUserMappers appUserMappers;
    private final KeyCloakService keyCloakService;
    @Override

    public AppUserDto createAppeUser(AppUser appUser) {



        log.info("Creating User: {} in keycloak", appUser.getFirstName());
        UUID uuid = keyCloakService.addKeycloakUser(appUser, Roles.app_user);
        appUser.setId(uuid);
        log.info("Creating User: {} in database", appUser.getFirstName());

        return appUserMappers.appUserToDto(appUserRepository.save(appUser));
    }

    @Override
    public Page<AppUserDto> getAllUsers(Pageable pageable) {
        return appUserRepository.findAll(pageable).map(appUserMappers::appUserToDto);
    }

    @Override
    public Page<AppUserDto> getUsersByName(String name, Pageable pageable) {
        Page<AppUserDto> usersByName = appUserRepository.findByfirstNameIgnoreCase(name,pageable).orElseThrow(() -> new RuntimeException());
        return usersByName;
    }

    @Override
    public AppUserDto getUserById(UUID id) {
        return appUserRepository.findById(id).map(appUserMappers::appUserToDto).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteUserById(UUID id) {
        UUID UserId = id;
        log.info("Removing user {} from keycloak", UserId);
        keyCloakService.removeKeycloakUser(UserId.toString());
        log.info("Removing User {} from icycle database", UserId);
        appUserRepository.deleteById(UserId);
        
    }
}
