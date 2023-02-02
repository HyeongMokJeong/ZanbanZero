package com.hanbat.zanbanzero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {

    @GetMapping("/api/cuser/login")
    public String login() {
        return "login";
    }
}
