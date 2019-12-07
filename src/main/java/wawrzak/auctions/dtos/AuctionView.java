package wawrzak.auctions.dtos;

import java.util.Objects;

public class AuctionView {

    private final long id;
    private final String title;

    public AuctionView(long id, String title) {
        this.id = id;
        this.title = title;

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionView that = (AuctionView) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "AuctionView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
