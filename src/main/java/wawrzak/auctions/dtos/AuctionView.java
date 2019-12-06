package wawrzak.auctions.dtos;

import java.util.Objects;

public class AuctionView {

    private final long id;
    private final String title;
    private final int answerCount;

    public AuctionView(long id, String title, int answerCount) {
        this.id = id;
        this.title = title;
        this.answerCount = answerCount;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuctionView that = (AuctionView) o;
        return id == that.id &&
                answerCount == that.answerCount &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, answerCount);
    }

    @Override
    public String toString() {
        return "AuctionView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answerCount=" + answerCount +
                '}';
    }
}
