package wawrzak.auctions.services;


import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class ContentService {

    private static final Detector DETECTOR = new DefaultDetector();

    private static final String IMAGE_TYPE = "image";

    public boolean isImage(byte[] bytes) throws IOException {
        var mediaType = DETECTOR.detect(new ByteArrayInputStream(bytes), new Metadata());
        return mediaType.getType().equals(IMAGE_TYPE);
    }

}
