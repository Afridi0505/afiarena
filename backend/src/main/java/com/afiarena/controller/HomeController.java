package com.afiarena.controller;

import com.afiarena.model.HomeCollections;
import com.afiarena.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController /* Controller will return page as old porject (jsp) rest will return the data*/
public class HomeController {
@Autowired
HomeService homeService;
    @RequestMapping("/")
    public String start(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        return "Welocme" + request.getSession().getId();
    }

    @GetMapping("/getList")
    public List<HomeCollections> getHomeCollections() {
        return homeService.getHomeCollections();
    }

    @PostMapping("/addList")
    public void addHomeCollections(@RequestBody HomeCollections homeCollections) {
           homeService.addHomeCollections(homeCollections);
    }

    @GetMapping("/getCSRFToken")
    public CsrfToken getCSRFToken(HttpServletRequest request) {
        return homeService.getCSRFToken(request);
    }
}
