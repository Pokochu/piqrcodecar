package com.poko.pi.car.controller;

import com.poko.pi.car.model.QRCodeResult;
import com.poko.pi.car.service.ImageService;
import com.poko.pi.car.service.ImgService;
import com.poko.pi.car.service.QRCodeDecoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/imageService")
public class ImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImgService imgService;
    @Autowired
    private QRCodeDecoderService decoderService;

    @GetMapping(path = "/decodeImage")
    public void decodeImage() {
        byte[] bytes = imageService.takePicture();
        LOGGER.info("Image taken to bytearray: {}, length: {}", bytes.toString(), bytes.length);
        QRCodeResult result = decoderService.decodeQRCode(bytes);
        LOGGER.info(result.toString());
    }

    @GetMapping(path = "/decodeWithImg")
    public void decodeWithImg() {
        byte[] bytes = imgService.takeBufferedStill();
        LOGGER.info("Image taken to bytearray: {}, length: {}", bytes.toString(), bytes.length);
        QRCodeResult result = decoderService.decodeQRCode(bytes);
        LOGGER.info(result.toString());
    }
}
