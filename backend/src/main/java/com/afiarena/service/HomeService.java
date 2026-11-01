package com.afiarena.service;

import com.afiarena.model.HomeCollections;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    List<HomeCollections> homeCollections = new ArrayList<>(List.of(
            new HomeCollections("afi", 1),
            new HomeCollections("bubu", 2)
    ));

    public List<HomeCollections> getHomeCollections(){
        return homeCollections;
    }
    public void addHomeCollections(HomeCollections homeCollection){
        homeCollections.add(homeCollection);
    }

    public CsrfToken getCSRFToken(HttpServletRequest httpServlet) {
        return (CsrfToken) httpServlet.getAttribute("_csrf");
    }
}

