package drawing;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ArcDrawer {

    public void drawArc(BufferedImage image, int centerX, int centerY, int radius,
                        double startAngle, double endAngle,
                        Color startColor, Color endColor) {

        startAngle = Math.toRadians(startAngle);
        endAngle = Math.toRadians(endAngle);

        // Определение направления обхода
        if (endAngle < startAngle) {
            endAngle += 2 * Math.PI;
        }

        double arcLength = endAngle - startAngle;

        // Алгоритм Брезенхема
        drawArcBresenham(image, centerX, centerY, radius, startAngle, endAngle,
                arcLength, startColor, endColor);
    }

    private void drawArcBresenham(BufferedImage image, int centerX, int centerY, int radius,
                                  double startAngle, double endAngle, double arcLength,
                                  Color startColor, Color endColor) {

        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        drawArcPoints(image, centerX, centerY, x, y, startAngle, endAngle,
                arcLength, startColor, endColor);

        while (y >= x) {
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }

            drawArcPoints(image, centerX, centerY, x, y, startAngle, endAngle,
                    arcLength, startColor, endColor);
        }
    }

    private void drawArcPoints(BufferedImage image, int centerX, int centerY, int x, int y,
                               double startAngle, double endAngle, double arcLength,
                               Color startColor, Color endColor) {

        drawPointIfInArc(image, centerX + x, centerY + y, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX - x, centerY + y, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX + x, centerY - y, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX - x, centerY - y, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX + y, centerY + x, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX - y, centerY + x, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX + y, centerY - x, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
        drawPointIfInArc(image, centerX - y, centerY - x, startAngle, endAngle,
                centerX, centerY, arcLength, startColor, endColor);
    }

    private void drawPointIfInArc(BufferedImage image, int x, int y,
                                  double startAngle, double endAngle,
                                  int centerX, int centerY, double arcLength,
                                  Color startColor, Color endColor) {

        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }

        double angle = Math.atan2(y - centerY, x - centerX);

        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        if (isAngleInRange(angle, startAngle, endAngle)) {
            double t = (angle - startAngle) / arcLength;

            Color interpolatedColor = ColorInterpolator.interpolate(startColor, endColor, t);

            image.setRGB(x, y, interpolatedColor.getRGB());
        }
    }

    private boolean isAngleInRange(double angle, double startAngle, double endAngle) {
        if (endAngle > 2 * Math.PI) {
            return angle >= startAngle || angle <= endAngle - 2 * Math.PI;
        }
        return angle >= startAngle && angle <= endAngle;
    }
}