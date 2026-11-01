package com.afiarena.repository;

import com.afiarena.model.GameCollections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamesRepository extends JpaRepository<GameCollections, Integer> {
    //JPQL - JAVA PERSISTENCE QUERY LANGUAGE use class name and entity field name
    @Query("SELECT g from GameCollections g WHERE " +
            "LOWER(g.gameName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(g.gameType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(g.imageName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<GameCollections> searchGamesByKeyword(String keyword);
}
