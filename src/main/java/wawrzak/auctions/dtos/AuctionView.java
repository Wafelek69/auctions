package wawrzak.auctions.dtos;

import wawrzak.auctions.model.Image;

import java.math.BigDecimal;
import java.util.Objects;

public class AuctionView {

    private final long id;
    private final String title;
    private final Image thumbnail;
    private final BigDecimal LastPrice;

    public AuctionView(long id, String title, Image thumbnail, BigDecimal LastPrice) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.LastPrice = LastPrice;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public BigDecimal getLastPrice() {
        return LastPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionView that = (AuctionView) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(thumbnail, that.thumbnail) &&
                Objects.equals(LastPrice, that.LastPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, thumbnail, LastPrice);
    }

    @Override
    public String toString() {
        return "AuctionView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail=" + thumbnail +
                ", lastprice=" + LastPrice +
                '}';
    }
}
