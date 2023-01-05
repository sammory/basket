package com.zerobase.zerolms.main.controller;

import com.zerobase.zerolms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {

//        mailComponents.sendMailTest();

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
