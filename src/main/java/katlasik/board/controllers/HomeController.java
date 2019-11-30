package katlasik.board.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

}
