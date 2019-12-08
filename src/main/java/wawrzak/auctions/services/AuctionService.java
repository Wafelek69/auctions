package wawrzak.auctions.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wawrzak.auctions.dtos.AuctionView;
import wawrzak.auctions.dtos.NewAuction;
import wawrzak.auctions.model.Auction;
import wawrzak.auctions.model.Image;
import wawrzak.auctions.repositories.AuctionRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final SecurityService securityService;
    private final ContentService contentService;


    public AuctionService(AuctionRepository auctionRepository, SecurityService securityService, ContentService contentService) {
        this.auctionRepository = auctionRepository;
        this.contentService = contentService;
        this.securityService = securityService;
    }

    public Optional<Auction> findWithbays(Long auctionId){
        return auctionRepository.findWithBays(auctionId);
    }
    public Page<AuctionView> findAuctionViews(Pageable page){
        return auctionRepository.findAuctionViews(page);
    }
    public List<AuctionView> findAuctionViewsByUserId(Long userId){
        return auctionRepository.findAuctionViewsById(userId);
    }

    private List<Image> processImages(MultipartFile[] files) {
        return Arrays.stream(files)
                .filter(not(MultipartFile::isEmpty))
                .map(file -> {
                    try {
                        var bytes = file.getBytes();
                        if (contentService.isImage(bytes)) {
                            return new Image(
                                    file.getOriginalFilename(),
                                    bytes,
                                    file.getContentType(),
                                    file.getSize()
                            );
                        } else {
                            throw new AccessDeniedException("Illegal mime type for image.");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public Auction createAuction(NewAuction newAuction) {
        var auction = new Auction();
        auction.setTitle(newAuction.getTitle());
        auction.setDescription(newAuction.getDescription());
        auction.setUser(securityService.getLoggedInUser());
        processImages(newAuction.getFiles()).forEach(auction::addImage);
        return auctionRepository.save(auction);
    }
}
