package com.poko.pi.car.service;

import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImgService.class);

    public byte[] takeBufferedStill() {
        try {
            RPiCamera camera = new RPiCamera("/home/pi/pictures");
            camera.setWidth(1024).setHeight(768);

            BufferedImage bufferedImage = camera.takeBufferedStill();
            return toByteArray(bufferedImage);
        } catch (FailedToRunRaspistillException | InterruptedException | IOException e) {
            LOGGER.error("Something went wrong: {}", e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }

    private byte[] toByteArray(BufferedImage image) {
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            ImageIO.write(image, "jpg", outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Something went wrong: {}", e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }
}
