package de.base.swing;

import de.base.awt.RunnableCanvas;

import javax.swing.*;
import java.awt.*;

public class ConfigurableGui extends JPanel {

    protected <T extends RunnableCanvas<JPanel>> ConfigurableGui(int width, int height, T runnableClass) {
        this.setSize(width, height);
        this.add(runnableClass);

        runnableClass.start();
        runnableClass.setGui(this);

        setVisible(true);
    }

    protected <T extends RunnableCanvas<JPanel>> ConfigurableGui(Dimension dimension, T runnableClass) {
        this.setSize(dimension);
        this.add(runnableClass);

        runnableClass.setGui(this);

        setVisible(true);

        runnableClass.start();
    }

    public static <T extends RunnableCanvas<JPanel>> JPanel create(int width, int height, T runnableClass) {
        return new ConfigurableGui(width, height, runnableClass);
    }

    public static <T extends RunnableCanvas<JPanel>> JPanel createFullScreen(T runnableClass) {
        return new ConfigurableGui(Toolkit.getDefaultToolkit().getScreenSize(), runnableClass);
    }
}
