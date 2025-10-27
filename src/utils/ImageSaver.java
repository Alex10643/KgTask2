package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {

    public static void saveImage(BufferedImage image, String filename) {
        try {
            File output = new File(filename);
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }
}