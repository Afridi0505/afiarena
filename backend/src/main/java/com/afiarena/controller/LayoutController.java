package com.afiarena.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LayoutController {

    @RequestMapping("/layout")
    public String startl() {
        return "Welocme to layout";
    }
    @RequestMapping("/new")
    public String start() {
        return "Welocme to layout";
    }
}
