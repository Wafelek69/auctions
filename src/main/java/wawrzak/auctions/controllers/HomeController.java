package wawrzak.auctions.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wawrzak.auctions.model.User;
import wawrzak.auctions.services.SecurityService;

@Controller
public class HomeController {

    final private SecurityService securityService;

    public HomeController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        model.addAttribute("user", securityService.getLoggedInUser());

        return "home";
    }

}
