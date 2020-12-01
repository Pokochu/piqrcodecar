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

    public byte[] takePicture() {
        CameraConfiguration configuration = CameraConfiguration.cameraConfiguration()
                .width(1024)
                .height(768)
                .automaticWhiteBalance(AutomaticWhiteBalanceMode.AUTO)
                .encoding(Encoding.JPEG)
                .quality(85);

        try(Camera camera = new Camera(configuration)){
            ByteArrayPictureCaptureHandler handler = new ByteArrayPictureCaptureHandler();
            camera.takePicture(handler);
            return handler.result();
        } catch (Exception e) {
            LOGGER.error("Cannot take picture: {}", e.getMessage());
            e.printStackTrace();
            return new byte[0];
        }
    }
}
