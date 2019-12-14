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
    private final ImageService imageService;


    public AuctionService(AuctionRepository auctionRepository, SecurityService securityService, ContentService contentService, ImageService imageService) {
        this.auctionRepository = auctionRepository;
        this.securityService = securityService;
        this.contentService = contentService;
        this.imageService = imageService;
    }

    public Optional<Auction> findWithbays(Long auctionId){
        return auctionRepository.findWithBays(auctionId);
    }
    public Page<AuctionView> findAuctionViews(Pageable page){
        return auctionRepository.findAuctionViews(page);
    }

    public Page<AuctionView> findQuestionViewsByUserId(Long userId, Pageable page) {
        return auctionRepository.findQuestionViewsById(userId, page);
    }

    public Page<AuctionView>BidAuction(Long userId, Pageable page){
        return auctionRepository.bidAuction(userId, page);
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
        auction.setLastPrice(newAuction.getLastPrice());
        var images = processImages(newAuction.getFiles());
        images.forEach(auction::addImage);
        if(!images.isEmpty()) {
            imageService.resize(images.get(0)).ifPresent(auction::setThumbnail);
        }
        return auctionRepository.save(auction);
    }
}
