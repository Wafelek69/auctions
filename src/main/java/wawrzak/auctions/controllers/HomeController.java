package wawrzak.auctions.controllers;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wawrzak.auctions.services.AuctionService;
import wawrzak.auctions.services.SecurityService;

@Controller
public class HomeController {

    private final SecurityService securityService;
    private final AuctionService auctionService;

    public HomeController(SecurityService securityService, AuctionService auctionService) {
        this.securityService = securityService;
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String getWelcome(Model model, Pageable page) {
        model.addAttribute("auctions", auctionService.findAuctionViews(page));

        return "welcome";
    }

    @GetMapping("/home")
    public String getHome(Model model, Pageable page) {

        model.addAttribute("user", securityService.getLoggedInUser());
        model.addAttribute("auctions", auctionService.findAuctionViews(page));
        return "home";
    }

}
