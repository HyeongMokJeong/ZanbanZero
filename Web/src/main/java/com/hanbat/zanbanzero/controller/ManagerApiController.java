package com.hanbat.zanbanzero.controller;

import com.hanbat.zanbanzero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerApiController {

    @Autowired
    UserService userService;

}
