package wawrzak.auctions.dtos;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class NewAuction {

    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull
    @NumberFormat(pattern = "#,##")
    private BigDecimal lastPrice;

    private MultipartFile[] files;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
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

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Override
    public String toString() {
        return "NewAuction{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastPrice=" + lastPrice +
                ", files=" + Arrays.toString(files) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewAuction that = (NewAuction) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(lastPrice, that.lastPrice) &&
                Arrays.equals(files, that.files);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, description, lastPrice);
        result = 31 * result + Arrays.hashCode(files);
        return result;
    }
}
