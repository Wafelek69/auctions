package wawrzak.auctions.dtos;

import org.springframework.format.annotation.NumberFormat;
import wawrzak.auctions.model.Image;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

public class NewBid {

    private final long auctionId;
    @NumberFormat(pattern = "#,##")
    private final BigDecimal newPrice;

    public NewBid(long auctionId, BigDecimal newPrice) {
        this.auctionId = auctionId;
        this.newPrice = newPrice;
    }

    public long getAuctionId() {
        return auctionId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewBid that = (NewBid) o;
        return auctionId == that.auctionId &&
                Objects.equals(newPrice, that.newPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionId, newPrice);
    }

    @Override
    public String toString() {
        return "AuctionView{" +
                "id=" + auctionId +
                ", title='" + newPrice + '\'' +
                '}';
    }
}
