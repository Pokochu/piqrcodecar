package com.poko.pi.car.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import uk.co.caprica.picam.*;
import uk.co.caprica.picam.enums.AutomaticWhiteBalanceMode;
import uk.co.caprica.picam.enums.Encoding;

@Service
public class ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    private CameraConfiguration configuration;

    public ImageService() {
        configuration = CameraConfiguration.cameraConfiguration()
                .width(1024)
                .height(768)
                .automaticWhiteBalance(AutomaticWhiteBalanceMode.AUTO)
                .encoding(Encoding.JPEG)
                .quality(85);
    }

    public byte[] takePicture() {
        byte[] picBytes = new byte[0];

        try(Camera camera = new Camera(configuration)){
            picBytes = camera.takePicture(new ByteArrayPictureCaptureHandler());
        } catch (CameraException | CaptureFailedException e) {
            LOGGER.error("Cannot take picture: {}", e.getMessage());
            e.printStackTrace();
        }
        return picBytes;
    }
}
