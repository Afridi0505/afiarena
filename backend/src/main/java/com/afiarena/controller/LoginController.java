package com.afiarena.controller;

import com.afiarena.model.GamesUsers;
import com.afiarena.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @Autowired
    LoginService loginService;
    @RequestMapping("login")
    public String verifyGameUsers(@RequestBody GamesUsers gamesUsers){
        return loginService.verifyGameUsers(gamesUsers);
    }
}
