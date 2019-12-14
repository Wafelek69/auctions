package wawrzak.auctions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import wawrzak.auctions.dtos.NewAuction;
import wawrzak.auctions.dtos.NewBid;
import wawrzak.auctions.services.AuctionService;
import wawrzak.auctions.services.SecurityService;

import javax.validation.Valid;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class AuctionController {

    private final AuctionService auctionService;
    private final SecurityService securityService;

    public AuctionController(AuctionService auctionService, SecurityService securityService) {
        this.auctionService = auctionService;
        this.securityService = securityService;
    }

    @GetMapping("/new-auction")
    public String getNewAuction(Model model) {

        var auction = new NewAuction();
        model.addAttribute("auction", auction);
        model.addAttribute("user", securityService.getLoggedInUser());

        return "new-auction";
    }
    @GetMapping("/auction/{auctionId}")
    public String getAuction(Model model, @PathVariable long auctionId) {

        var maybeAuction = auctionService.findWithbays(auctionId);

        if(maybeAuction.isPresent()) {
            model.addAttribute("auction", maybeAuction.get());
            model.addAttribute("newBid", new NewBid(maybeAuction.get().getId(), BigDecimal.ZERO));


            return "auction";
        } else {
            throw  new ResponseStatusException(NOT_FOUND, "Auction not found");
        }
    }

    @PostMapping("/new-auction")
    public String postQuestion(@ModelAttribute("auction") @Valid NewAuction auction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", securityService.getLoggedInUser());
            return "new-auction";
        } else {
            var persisted = auctionService.createAuction(auction);
            return "redirect:/auction/" + persisted.getId();
        }
    }

    @PostMapping("/bid")
    public String postBid(@ModelAttribute("newBid") @Valid NewBid bid, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/auction/" + bid.getAuctionId();
        } else {
            auctionService.updatePrice(bid);
            return "redirect:/auction/" + bid.getAuctionId();
        }
    }

}
