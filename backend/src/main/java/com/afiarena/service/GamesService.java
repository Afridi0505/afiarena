package com.afiarena.service;

import com.afiarena.model.GameCollections;
import com.afiarena.model.GamesUsers;
import com.afiarena.repository.GamesRepository;
import com.afiarena.repository.GamesUsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GamesService {
@Autowired
public GamesRepository gamesRepository;

@Autowired
public GamesUsersRepository gamesUsersRepository;

    public CsrfToken getCSRFToken(HttpServletRequest httpServlet) {
        return (CsrfToken) httpServlet.getAttribute("_csrf");
    }
//    List<GameCollections> gameCollections = new ArrayList<>(Arrays.asList(
//            new GameCollections(01,"GodOfWar","Action"),
//            new GameCollections(02,"Asphalt","Racing")));

    public List<GameCollections> getGamesCollections() {
        return gamesRepository.findAll();
    }

    public GameCollections getGamesCollectionsById(int gameNumber){
        return gamesRepository.findById(gameNumber).orElse(null);
//        return gameCollections.stream()
//                .filter(game -> game.getGameNumber() == gameNumber)
//                .findFirst()
//                .orElse(new GameCollections(0, "unknownGame", "unknownType"));
    }

    public GameCollections addGameCollections(GameCollections gameCollection, MultipartFile multipartFile) throws IOException {
        gameCollection.setImageName(multipartFile.getOriginalFilename());
        gameCollection.setImageType(multipartFile.getContentType());
        gameCollection.setImageData(multipartFile.getBytes());
        return gamesRepository.save(gameCollection);
//           gameCollections.add(gameCollection);
    }
//    public int getExpectedIndex(int gameNumber) {
//        for(int i=0;i<gameCollections.size();i++){
//            if(gameCollections.get(i).getGameNumber() == gameNumber)
//                return i;
//        }
//        return -1;
//    }
//    public GameCollections updateGameCollections(int gameIndex, GameCollections gameCollection) {
//        return gamesRepository.save(gameCollection);
////        gameCollections.set(getExpectedIndex(gameCollection.getGameNumber()),gameCollection);
//    }
    public GameCollections updateGameCollections(int gameIndex, GameCollections gameCollection, MultipartFile multipartFile) throws IOException{
        gameCollection.setImageName(multipartFile.getOriginalFilename());
        gameCollection.setImageType(multipartFile.getContentType());
        gameCollection.setImageData(multipartFile.getBytes());
        return gamesRepository.save(gameCollection);
//        gameCollections.set(getExpectedIndex(gameCollection.getGameNumber()),gameCollection);
    }

    public void deleteGamesCollectionsById(int gameIndex) {
         gamesRepository.deleteById(gameIndex);
//        gameCollections.remove(getExpectedIndex(gameNumber));
    }

    public GamesUsers registerGameUser(GamesUsers gamesUsers) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        gamesUsers.setGameUserPassword(bCryptPasswordEncoder.encode(gamesUsers.getGameUserPassword()));
        return gamesUsersRepository.save(gamesUsers);
    }
}
