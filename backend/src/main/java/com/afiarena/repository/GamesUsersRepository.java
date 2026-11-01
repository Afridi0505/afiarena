package com.afiarena.repository;

import com.afiarena.model.GamesUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesUsersRepository extends JpaRepository<GamesUsers, Integer> {
    GamesUsers findByGameUserName(String gameUserName);
}
