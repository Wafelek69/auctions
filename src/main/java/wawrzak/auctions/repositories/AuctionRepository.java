package wawrzak.auctions.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import wawrzak.auctions.dtos.AuctionView;
import wawrzak.auctions.model.Auction;

import java.util.Optional;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository <Auction, Long>, JpaRepository<Auction, Long> {

    @Query("select a from Auction a join fetch a.user left join fetch a.images where a.id=?1 order by a.createdOn desc")
    Optional<Auction> findWithBays(Long id);

    @Query("select new wawrzak.auctions.dtos.AuctionView(a.id, a.title, a.thumbnail, a.lastPrice) from Auction a order by a.createdOn desc ")
    Page<AuctionView> findAuctionViews(Pageable page);

    @Query("select new wawrzak.auctions.dtos.AuctionView(a.id, a.title, a.thumbnail, a.lastPrice) from Auction a where a.user.id = ?1 order by a.createdOn desc ")
    Page<AuctionView> findQuestionViewsById(long usedId, Pageable page);

    @Query("select new wawrzak.auctions.dtos.AuctionView(a.id, a.title, a.thumbnail, a.lastPrice) from Auction a where a.user.id = ?1 order by a.createdOn desc ")
    Page<AuctionView> bidAuction(long usedId, Pageable page);

}
