package de.base.awt;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Objects;

public abstract class RunnableFrame extends Frame implements Runnable {
    private final WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            Objects.requireNonNull(e);
            Window w = e.getWindow();
            if (w instanceof RunnableFrame frame) {
                frame.stop();
                return;
            }
            System.exit(130);
        }
    };

    private final int FPS;
    private Thread thread;
    private boolean running;
    private Point lastMousePos;

    public RunnableFrame() {
        super();
        this.FPS = 1000000000 / 60;
        this.setSize(1000, 750);
        this.setLocationRelativeTo(null);
        this.lastMousePos = new Point(-1, -1);
    }

    public RunnableFrame(String title, Dimension size, int fps) {
        super(title);
        this.FPS = 1000000000 / fps;
        this.setSize(size);
        this.setLocationRelativeTo(null);
        this.lastMousePos = new Point(-1, -1);
    }

    public RunnableFrame(int fps, boolean fullScreen) {
        super();
        this.FPS = 1000000000 / fps;
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.lastMousePos = new Point(-1, -1);
    }

    public void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
        this.requestFocus(FocusEvent.Cause.ACTIVATION);
        this.setVisible(true);
    }

    public void stop() {
        try {
            running = false;
            thread.join();
            this.setVisible(false);
            System.exit(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        running = true;
        thread.notify();
        this.requestFocus(FocusEvent.Cause.ACTIVATION);
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (double)(now - lastTime) / FPS;
            lastTime = now;

            while (delta >= 1) {
                render();
                tick();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }

    protected Graphics getGraphics(int buffersNeeded) {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(buffersNeeded);
            bufferStrategy = this.getBufferStrategy();
        }

        return bufferStrategy.getDrawGraphics();
    }

    protected abstract void init();

    protected abstract void tick();

    protected abstract void render();

    @Override
    public Point getMousePosition() {
        Point mouse = super.getMousePosition();

        mouse = (mouse == null ? this.lastMousePos : mouse);

        if (mouse != null)
            this.lastMousePos = mouse;

        return mouse;
    }
}
