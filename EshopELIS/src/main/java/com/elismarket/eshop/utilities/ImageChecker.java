package com.elismarket.eshop.utilities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class ImageChecker {

    private final static String format = ".jpg";

    private static BufferedImage retrieveImage(String path, String name) {
        try {
            return ImageIO.read(new File(path + name + format));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //in frontend call data:image/png;base64,{{ converted image code }}
    private static String imageToString(String type, String path, String nameOfImage) throws IOException {
        BufferedImage image = retrieveImage(path, nameOfImage);

        if (image == null)
            throw new IOException("Image not found");

        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static BufferedImage stringToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
