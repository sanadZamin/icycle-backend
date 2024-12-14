package com.joprog.icycle.reposotiry;

import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    Optional<Page<AppUserDto>> findByfirstNameIgnoreCase(String firstName, Pageable pageable);
}
