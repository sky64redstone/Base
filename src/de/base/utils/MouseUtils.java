package com.omnom.graphics;

import org.jetbrains.annotations.NotNull;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class MouseUtils {
    // Private Constructor that no one can initialise it
    private MouseUtils() {}
    // Sets the Cursor invisible
    public static void setCursorInvisible(Component component) {
        setCustomCursor(component, new BufferedImage(1, 1, BufferedImage.TRANSLUCENT), "InvisibleCursor");
    }
    // Sets the Cursor default / visible
    public static void setDefaultCursor(@NotNull Component component) {
        component.setCursor(Cursor.getDefaultCursor());
    }
    // Sets the Cursor into a Crosshair
    public static void setCrosshairCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }
    // Sets the Cursor into the Text-Cursor
    public static void setTextCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }
    // Sets the Cursor into the Wait-Cursor
    public static void setWaitCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setSWResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setSEResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setNWResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setNEResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setNResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setSResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setWResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
    }
    // Sets the Cursor into a Resize-Cursor
    public static void setEResizeCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
    }
    // Sets the Cursor into the Hand-Cursor
    public static void setHandCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    // Sets the Cursor into the Move-Cursor
    public static void setMoveCursor(@NotNull Component component) {
        component.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
    // Sets the Cursor into another Cursor
    public static void setCursor(@NotNull Component component, Cursor cursor) {
        component.setCursor(cursor);
    }
    // Sets the Cursor into a custom Cursor
    public static void setCustomCursor(@NotNull Component component, BufferedImage cursorImage, String name) {
        component.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), name));
    }
    // Sets the Position of the Cursor at the Center of the Screen
    public static void setMouseCenter(@NotNull Component component) {
        try {
            new Robot().mouseMove((int) ((component.getWidth() / 2) + component.getLocationOnScreen().getX()), (int) ((component.getHeight() / 2) + component.getLocationOnScreen().getY()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Sets the Position of the Cursor at a specific Point relative to the Screen
    public static void setMousePos(@NotNull Component component, @NotNull Point p) {
        try {
            new Robot().mouseMove((int) (p.getX() + component.getLocationOnScreen().getX()), (int) (p.getY() + component.getLocationOnScreen().getY()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Sets the Position of the Cursor at specific Coordinates relative to the Screen
    public static void setMousePos(@NotNull Component component, int x, int y) {
        try {
            new Robot().mouseMove((int) (x + component.getLocationOnScreen().getX()), (int) (y + component.getLocationOnScreen().getY()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Press Mouse Button - see also: InputEvent.BUTTON1_DOWN_MASK & InputEvent.BUTTON2_DOWN_MASK & InputEvent.BUTTON3_DOWN_MASK
    public static void pressMouse(int keyCode, int msDelay) {
        try {
            Robot r = new Robot();
            r.mousePress(keyCode);
            r.delay(msDelay);
            r.mouseRelease(keyCode);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Scrolls the Mouse Wheels
    public static void scrollMouseWheel(int count) {
        try {
            new Robot().mouseWheel(count);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Sets the Position of the Cursor at a specific Point
    public static void setMousePosition(@NotNull Point point) {
        try {
            new Robot().mouseMove(point.x, point.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // Sets the Position of the Cursor at specific Coordinates
    public static void setMousePosition(int x, int y) {
        try {
            new Robot().mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
