package wawrzak.auctions.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private User user;

    @Size(min = 10, max = 100)
    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @ElementCollection
    @CollectionTable(name = "auction_images")
    private Set<Image> images = new HashSet<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "thumbnail_name")),
            @AttributeOverride(name = "contentType", column = @Column(name = "thumbnail_content_type")),
            @AttributeOverride(name = "image", column = @Column(name = "thumbnail")),
            @AttributeOverride(name = "size", column = @Column(name = "thumbnail_size"))
    })
    private Image thumbnail;


    public void addImage(Image image) {
        images.add(image);
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id &&
                Objects.equals(user, auction.user) &&
                Objects.equals(title, auction.title) &&
                Objects.equals(description, auction.description) &&
                Objects.equals(createdOn, auction.createdOn) &&
                Objects.equals(images, auction.images) &&
                Objects.equals(thumbnail, auction.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, description, createdOn, images, thumbnail);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", images=" + images +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
