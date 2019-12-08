package wawrzak.auctions.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Embeddable
public class Image {

    @NotNull
    String name;

    @NotNull
    @Lob
    byte[] image;

    @NotNull
    String contentType;

    @NotNull
    long size;

    protected Image() {}

    public Image(String name, byte[] image, String contentType, long size) {
        this.name = name;
        this.image = image;
        this.contentType = contentType;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public long getSize() {
        return size;
    }

    public String getContentType() {
        return contentType;
    }

    public String asByte64() {
        return String.format("data:%s;base64,%s", contentType, new String(Base64.getEncoder().encode(image)));
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return size == image1.size &&
                Objects.equals(name, image1.name) &&
                Objects.equals(contentType, image1.contentType) &&
                Arrays.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, size, contentType);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
