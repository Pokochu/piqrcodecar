package com.poko.pi.car.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.poko.pi.car.model.QRCodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class QRCodeDecoderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeDecoderService.class);

    private QRCodeReader qrCodeReader;

    public QRCodeDecoderService() {
        qrCodeReader = new QRCodeReader();
    }

    public QRCodeResult decodeQRCode(byte[] picBytes) {
        QRCodeResult result = new QRCodeResult();
        try {
            Result decode = getResultFromByteArray(picBytes);
            result.setNodeId(decode.getText());
            calculateOffset(result, decode.getResultPoints());
            LOGGER.info("Text: {}", decode.getText());
            LOGGER.info("MetaData: {}", decode.getResultMetadata().toString());
            LOGGER.info("ResultPoints: {}", Arrays.toString(decode.getResultPoints()));
        } catch (IOException | NotFoundException | ChecksumException | FormatException e) {
            LOGGER.error("Error in something: {}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private Result getResultFromByteArray(byte[] picBytes) throws FormatException, ChecksumException, NotFoundException, IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(picBytes));
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        return qrCodeReader.decode(bitmap);
    }

    private void calculateOffset(QRCodeResult result, ResultPoint[] resultPoints) {

    }
}
