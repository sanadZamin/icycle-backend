package com.joprog.icycle.service;

import com.joprog.icycle.config.KeyCloakConfigData;
import com.joprog.icycle.entity.AppUser;
import com.joprog.icycle.entity.Roles;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class KeyCloakService {
    private final KeyCloakConfigData keycloakConfigData;

    public Keycloak getKeycloakInstance() {
        return    KeycloakBuilder.builder()
                .serverUrl(keycloakConfigData.getAuthServerUrl())
                .realm(keycloakConfigData.getRealm())
                .clientId("web_client")
                .username("sanad.amin@gmail.com")
                .password("Umniah@123")
                .build();
    }

    public UUID addKeycloakUser(AppUser appUserDTO, Roles role) {
        Keycloak keycloak = getKeycloakInstance();
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(appUserDTO.getMobileNumber());
        user.setFirstName(appUserDTO.getFirstName());
        user.setLastName(appUserDTO.getLastName());
        user.setEmail(appUserDTO.getEmail());
        List<String> actionList = new ArrayList<>();
        actionList.add("Update Profile");
        actionList.add("Update Password");
        user.setRequiredActions(actionList);
        // Get realm
        RealmResource realmResource = keycloak.realm(keycloakConfigData.getRealm());
        UsersResource usersRessource = realmResource.users();


        Response response = usersRessource.create(user);

        if(response.getStatus() == 409){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Already Availble");
        }
        String userId = CreatedResponseUtil.getCreatedId(response);
        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(true);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("test@123");
        UserResource userResource = usersRessource.get(userId);
        // Set password credential
        user.singleAttribute("userId",userId);
        userResource.update(user);
        userResource.resetPassword(passwordCred);
        userResource.sendVerifyEmail();
        log.info(realmResource.roles().toString());
        RoleRepresentation testerRealmRole = realmResource.roles()
                .get(String.valueOf(role)).toRepresentation();
        // Assign realm role tester to user
        userResource.roles().realmLevel() //
                .add(Arrays.asList(testerRealmRole));

        return UUID.fromString(userId);
    }


    public void removeKeycloakUser(String userId) {
        Keycloak keycloak = getKeycloakInstance();
        RealmResource realmResource = keycloak.realm(keycloakConfigData.getRealm());
        UsersResource usersRessource = realmResource.users();
        UserResource userResource = usersRessource.get(userId);
        userResource.remove();
    }



}
