package de;

import de.base.awt.RunnableFrame;

import java.awt.*;

public class Main extends RunnableFrame {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super("Test-Frame", new Dimension(1000, 750), 60);
        start();
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void tick() {

    }

    @Override
    protected void render() {

    }
}
