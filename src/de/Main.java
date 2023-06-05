package de;

import de.base.awt.Colors;
import de.base.awt.RunnableCanvas;
import de.base.swing.ConfigurableGui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.Graphics;

public class Main extends RunnableCanvas<JPanel> {

    public static void main(String[] args) {
        ConfigurableGui.createFullScreen(new Main());
    }

    @Override
    public void paint(@NotNull Graphics g) {
        g.setColor(Colors.darker_gray);
        this.fillBackground(g);
    }

    @Override
    protected void tick() {
        this.update();
    }

    @Override
    protected void init() {
        super.init();
    }
}
