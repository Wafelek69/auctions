package wawrzak.auctions.services;

import org.springframework.stereotype.Service;
import wawrzak.auctions.dtos.AuctionView;
import wawrzak.auctions.dtos.NewAuction;
import wawrzak.auctions.model.Auction;
import wawrzak.auctions.repositories.AuctionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final SecurityService securityService;

    public AuctionService(AuctionRepository auctionRepository, SecurityService securityService) {
        this.auctionRepository = auctionRepository;
        this.securityService = securityService;
    }

//    public Optional<Auction> findWithbays(Long auctionId){
//        return auctionRepository.findWithBays(auctionId);
//    }
    public List<AuctionView> findAuctionViews(){
        return auctionRepository.findAuctionViews();
    }
    public List<AuctionView> findAuctionViewsByUserId(Long userId){
        return auctionRepository.findAuctionViewsById(userId);
    }

    public Auction createAuction(NewAuction newAuction) {
        var auction = new Auction();
        auction.setTitle(newAuction.getTitle());
        auction.setDescription(newAuction.getDescription());
        auction.setUser(securityService.getLoggedInUser());
        return auctionRepository.save(auction);
    }
}
