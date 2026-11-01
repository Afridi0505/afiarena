package com.afiarena.service;

import com.afiarena.model.GamesUsers;
import com.afiarena.repository.GamesUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GamesUserDetailsService implements UserDetailsService {
    @Autowired
    GamesUsersRepository gamesUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String gameUserName) throws UsernameNotFoundException {
        GamesUsers gamesUsers = gamesUsersRepository.findByGameUserName(gameUserName);
        if(gamesUsers == null) {
            System.out.println("USER NOT FOUND");
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
        return new GamesCustomizeUsers(gamesUsers);
    }
}
