package com.afiarena.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor /*public GameCollections(int gameNumber,
                       String gameName,
                       String gameType) {
    ...
} */
@NoArgsConstructor /* new GameCollections() or without lombok public GameCollections() {} */
@Entity
@Component
@Table(name = "gamecollections")
public class GameCollections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*ID SHOULD NOT BE PASSSED FROM THE FE AND THIS ANNOTATION WILL MARK THIS FIELD AS AUTO GEERATION AND
    NOT THE AUTO INCREMENT ON DB ITSELF BECAUSE ON DB WE HAVE TO MANUALLY EXECUTE THE QUERY*/
    @Column(name = "id")
    private Integer gameNumber;
    @Column(name = "game_name")
    private String gameName;
    @Column(name = "genre")
    private String gameType;
    @Column(name="image_name")
    private String imageName;
    @Column(name="image_type")
    private String imageType;
    @Column(name="image_data", columnDefinition = "LONGBLOB")
    @Lob /*tinyblob if no lob varbinary(255)*/
    private byte[] imageData;
}
