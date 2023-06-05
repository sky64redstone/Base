package de.base.utils;

import de.base.awt.geom.Circle;
import org.jetbrains.annotations.NotNull;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Shape;

public class GraphicsUtils {

    private GraphicsUtils() {
    }

    public static void fillRect(@NotNull Graphics graphics, @NotNull Rectangle rectangle) {
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void drawRect(@NotNull Graphics graphics, @NotNull Rectangle rectangle) {
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void clearRect(@NotNull Graphics graphics, @NotNull Rectangle rectangle) {
        graphics.clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public static void drawCircle(@NotNull Graphics graphics, int x, int y, int radius) {
        graphics.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void drawCircle(Graphics graphics, @NotNull Point point, int radius) {
        drawCircle(graphics, point.x, point.y, radius);
    }

    public static void fillCircle(@NotNull Graphics graphics, int x, int y, int radius) {
        graphics.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void fillCircle(Graphics graphics, @NotNull Point point, int radius) {
        fillCircle(graphics, point.x, point.y, radius);
    }

    public static void drawPoint(@NotNull Graphics graphics, @NotNull Point point) {
        graphics.drawRect(point.x, point.y, 1, 1);
    }

    public static void drawPoint(@NotNull Graphics graphics, int x, int y) {
        graphics.drawRect(x, y, 1, 1);
    }

    public static void drawShape(Graphics graphics, Shape shape) {
        if (!(shape instanceof Circle c)) {
            Graphics2D g2D = (Graphics2D) graphics;
            g2D.draw(shape);
            return;
        }
        drawCircle(graphics, c.getX(), c.getY(), c.getRadius());
    }
}
