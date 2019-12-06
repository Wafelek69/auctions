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

//    @Query("select q from Question q left join fetch q.user u left join fetch q.answers a left join fetch a.user where q.id = ?1 order by q.createdOn")
//    Optional<Auction> findWithBays(Long id);

    @Query("select new wawrzak.auctions.dtos.QuestionView(q.id, q.title, size(a)) from Question q left join q.answers a group by q.id, q.title order by q.createdOn desc ")
    List<AuctionView> findAuctionViews();

    @Query("select new wawrzak.auctions.dtos.QuestionView(q.id, q.title, size(a)) from Question q left join q.answers a where q.user.id = ?1 group by q.id, q.title order by q.createdOn desc ")
    List<AuctionView> findAuctionViewsById(long usedId);
}
