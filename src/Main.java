import drawing.ArcDrawer;
import utils.ImageSaver;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        // Создание изображения
        int width = 800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Заливка фона белым цветом
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }

        // Создание рисовальщика дуг
        ArcDrawer arcDrawer = new ArcDrawer();

        // Примеры дуг с интерполяцией

        arcDrawer.drawArc(image, 400, 300, 150, 45, 270,
                Color.RED, Color.BLUE);

        arcDrawer.drawArc(image, 200, 200, 80, 0, 90,
                Color.GREEN, Color.YELLOW);

        arcDrawer.drawArc(image, 600, 400, 100, 0, 360,
                Color.MAGENTA, Color.CYAN);

        arcDrawer.drawArc(image, 300, 400, 120, 180, 450,
                Color.ORANGE, Color.PINK);

        // Сохранение изображения в папке проекта
        ImageSaver.saveImage(image, "arc_examples.png");
        System.out.println("Изображение сохранено как 'arc_examples.png'");
    }
}