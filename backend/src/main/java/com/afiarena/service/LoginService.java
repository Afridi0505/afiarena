package com.afiarena.service;

import com.afiarena.model.GamesUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


    public String verifyGameUsers(GamesUsers gamesUsers) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(gamesUsers.getGameUserName(),gamesUsers.getGameUserPassword())); /* once user is verified we are
                generating the token here*/
        if(authentication.isAuthenticated()) return jwtService.generateToken(gamesUsers.getGameUserName());
        return "login failed";
    }

    public void logoutUser(GamesUsers gamesUser) {

    }
}
