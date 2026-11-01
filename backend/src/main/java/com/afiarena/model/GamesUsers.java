package com.afiarena.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gameUsers")
public class GamesUsers {
    @Id
    private Integer id;
    @Column(name = "game_user_name")
    private String gameUserName;
    @Column(name="game_user_password")
    private String gameUserPassword;
}
