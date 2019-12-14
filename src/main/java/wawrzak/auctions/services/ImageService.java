package wawrzak.auctions.services;


import org.springframework.stereotype.Service;
import wawrzak.auctions.model.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private static final int THUMBNAIL_HEIGHT = 100;
    private static final int THUMBNAIL_WIDTH = 100;

    public Optional<Image> resize(Image image) {
        ByteArrayInputStream in = new ByteArrayInputStream(image.getImage());
        try {
            BufferedImage img = ImageIO.read(in);

            var scaledImage = img.getScaledInstance(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, BufferedImage.SCALE_FAST);
            BufferedImage imageBuff = new BufferedImage(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "jpg", buffer);

            return Optional.of(new Image(image.getName(), buffer.toByteArray(), image.getContentType(), buffer.size()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}