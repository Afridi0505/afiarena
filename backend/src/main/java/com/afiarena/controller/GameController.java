package com.afiarena.controller;

import com.afiarena.model.GameCollections;
import com.afiarena.model.GamesUsers;
import com.afiarena.repository.GamesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.afiarena.service.GamesService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("games")
public class GameController {
    @Autowired
    private GamesService gamesService;
    @Autowired
    private GamesRepository gamesRepository;

    @RequestMapping("/register-user")
    public GamesUsers registerGameUser(@RequestBody GamesUsers gamesUsers){
        return gamesService.registerGameUser(gamesUsers);
    }
    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameCollections>> getGamesCollections() {
        return new ResponseEntity<>(gamesService.getGamesCollections(), HttpStatus.OK);
    }

    @GetMapping("/getGamesById/{gameNumber}")
    public ResponseEntity<GameCollections> getGamesCollectionsById(@PathVariable int gameNumber) {
        return new ResponseEntity<>(gamesService.getGamesCollectionsById(gameNumber), HttpStatus.OK);
    }

//    @PostMapping("/addGames")
//    public ResponseEntity<GameCollections> addGameCollections(@RequestBody GameCollections gameCollections) {
//                return new ResponseEntity<>(gamesService.addGameCollections(gameCollections), HttpStatus.OK);
//    }
    @PostMapping("/addGames")
    public ResponseEntity<?> addGameCollections(@RequestPart GameCollections gameCollections,
                                                              @RequestPart MultipartFile multipartFile) {
        try{
            return new ResponseEntity<>(gamesService.addGameCollections(gameCollections,multipartFile), HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    @PutMapping("/updateGames/{gameIndex}")
//    public ResponseEntity<GameCollections> updateGameCollections(@PathVariable int gameIndex,
//                                      @RequestBody GameCollections gameCollections) {
//        return new ResponseEntity<>(gamesService.updateGameCollections(gameIndex, gameCollections), HttpStatus.OK);
//    }
    @PutMapping("/updateGames/{gameIndex}")
    public ResponseEntity<?> updateGameCollections(@PathVariable int id,
                                                @RequestPart GameCollections gameCollections,
                                                @RequestPart MultipartFile multipartFile) {
        try {
            return new ResponseEntity<>(gamesService.updateGameCollections(id,gameCollections,multipartFile), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @DeleteMapping("/deleteGames/{gameIndex}")
    public void deleteGamesCollectionsById(@PathVariable int gameIndex) {
           gamesService.deleteGamesCollectionsById(gameIndex);
    }

    @GetMapping("/getGameImageById/{gameNumber}")
    public ResponseEntity<byte[]> getGameImageById(@PathVariable int gameNumber){
        GameCollections gameCollection = gamesService.getGamesCollectionsById(gameNumber);
        byte[] gameImage = gameCollection.getImageData();

        return ResponseEntity.ok()
                .body(gameImage);
    }
    @GetMapping("/searchGames")
    public ResponseEntity<List<GameCollections>> searchGamesByKeyword(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                gamesRepository.searchGamesByKeyword(keyword)
        );
    }
    @GetMapping("/getCSRFToken")
    public CsrfToken getCSRFToken(HttpServletRequest request) {
        return gamesService.getCSRFToken(request);
    }
}
