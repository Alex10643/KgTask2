package drawing;

import java.awt.Color;

public class ColorInterpolator {
    public static Color interpolate(Color startColor, Color endColor, double t) {
        t = Math.max(0, Math.min(1, t));

        // Интерполируем каждый компонент цвета
        int red = (int) (startColor.getRed() + t * (endColor.getRed() - startColor.getRed()));
        int green = (int) (startColor.getGreen() + t * (endColor.getGreen() - startColor.getGreen()));
        int blue = (int) (startColor.getBlue() + t * (endColor.getBlue() - startColor.getBlue()));

        return new Color(red, green, blue);
    }
}