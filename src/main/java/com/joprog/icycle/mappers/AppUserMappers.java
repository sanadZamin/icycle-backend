package com.joprog.icycle.mappers;

import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import org.springframework.stereotype.Component;


@Component
public class AppUserMappers {


    public AppUserDto appUserToDto(AppUser appUser){
        return new AppUserDto(
                appUser.getId(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                appUser.getRole()
        );
    }
}
