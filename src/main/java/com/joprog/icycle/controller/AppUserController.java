package com.joprog.icycle.controller;

import com.joprog.icycle.Exceptions.EmailValidationException;
import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import com.joprog.icycle.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.joprog.icycle.util.RegexHelper;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
@Data
public class AppUserController {
    private final AppUserService appUserService;
    private final RegexHelper regexHelper;
    @PostMapping
    @Operation(description = "Insert a new User in the database")
    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUser appUser, Principal principal) throws EmailValidationException {
        boolean isEmailValid = regexHelper.patternMatches(appUser.getEmail(), "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if(!isEmailValid){
            throw new EmailValidationException("Email Format Not Valid");
        }

        AppUserDto appeUser = appUserService.createAppeUser(appUser);
        log.info("User {} has been created successfully",appeUser.id());
        return new ResponseEntity<>(appeUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<AppUserDto>> getAllUsers(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size)
    {
        log.info("Getting pagenumber {} , pageSize {}",page,size);

        return new ResponseEntity<>(appUserService.getAllUsers(PageRequest.of(page,size)),HttpStatus.OK);
    }

    @RequestMapping(path = "getByName/{userName}",method = RequestMethod.GET)
    public ResponseEntity<Page<AppUserDto>> getUsersByName(@PathVariable String userName)
    {
        return new ResponseEntity<>(appUserService.getUsersByName(userName,PageRequest.of(0,100)),HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ResponseEntity<AppUserDto> getUserById(@PathVariable UUID userId){
        return new ResponseEntity<>(appUserService.getUserById(userId),HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable UUID userId){
        appUserService.deleteUserById(userId);
        return new ResponseEntity<>("User "+userId+" Deleted Successfully",HttpStatus.OK);
    }
}
