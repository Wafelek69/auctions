package wawrzak.auctions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import wawrzak.auctions.dtos.AuctionView;
import wawrzak.auctions.model.Auction;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository <Auction, Long>, JpaRepository<Auction, Long> {

    @Query("select new wawrzak.auctions.dtos.AuctionView(a.id, a.title) from Auction a order by a.createdOn desc ")
    List<AuctionView> findAuctionViews();

    @Query("select new wawrzak.auctions.dtos.AuctionView(a.id, a.title) from Auction a order by a.createdOn desc ")
    List<AuctionView> findAuctionViewsById(long usedId);
}
