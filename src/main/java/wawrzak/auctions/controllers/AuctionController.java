package wawrzak.auctions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import wawrzak.auctions.dtos.NewAuction;
import wawrzak.auctions.services.AuctionService;

import javax.validation.Valid;

@Controller
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/new-auction")
    public String getNewAuction(Model model) {

        var auction = new NewAuction();
        model.addAttribute("auction", auction);

        return "new-auction";
    }
    @GetMapping("/auction/{auctionId}")
    public String getAuction(Model model, @PathVariable long auctionId) {

        var maybeAuction = auctionService.findWithbays(auctionId);

        model.addAttribute("auction", auctionId);

        return "auction";
    }

    @PostMapping("/new-auction")
    public String postQuestion(@ModelAttribute("auction") @Valid NewAuction auction, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-auction";
        } else {
            var persisted = auctionService.createAuction(auction);
            return "redirect:/auction/" + persisted.getId();
        }
    }

}
