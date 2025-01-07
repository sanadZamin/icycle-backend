package com.joprog.icycle.reposotiry;

import com.joprog.icycle.dto.AppUserDto;
import com.joprog.icycle.entity.AppUser;
import com.joprog.icycle.entity.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamsRepository extends JpaRepository<Teams, UUID> {

    Optional<Teams> findByTeamName(String teamName);

    Page<Teams> findAll(Pageable pageable);

    Page<Teams> findByTeamNameContaining(String teamName, Pageable pageable);

    void deleteById(UUID id);
}
