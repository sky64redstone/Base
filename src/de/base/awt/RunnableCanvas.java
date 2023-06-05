package de.base.awt;

import de.base.utils.GraphicsUtils;

import java.awt.*;

/**
 * This class combines the Runnable interface and the Canvas class for easy control of the {@link Gui}
 */
public class RunnableCanvas<T extends Component> extends Canvas implements Runnable {
    // The Size of the Screen
    protected Rectangle screen = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    // This is used for the run() Methode
    protected Thread thread;
    // Uses this boolean to show if the Canvas is still running
    protected boolean running;
    // 10 is the amount of the ticks (you can change that if you want to)
    protected double amountOfTicks = 1000000000 / 60d;
    // If you use getGraphics() and super.getGraphics() will return null then is needs this Integer
    protected int buffersNeeded = 3;
    // It's like a JFrame or a JPanel
    protected T gui;

    public void start() {
        screen = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void stop() {
        try {
            running = false;
            thread.join();
            System.exit(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / amountOfTicks;
            lastTime = now;

            while (delta >= 1) {
                render(); // use this methode or use Component.paint(Graphics g)
                tick();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }

    protected void init() {
    }

    protected void tick() {
        update();
    }

    protected void render() {
        // An Example:
        /*
        Graphics graphics = getGraphics();

        //Draw here
        drawBackground(graphics, Color.white);

        graphics.dispose();
        this.getBufferStrategy().show();
         */
    }

    protected void fillBackground(Graphics g) {
        GraphicsUtils.fillRect(g, screen);
    }

    @Override
    public Point getMousePosition() {
        Point p = super.getMousePosition();
        if (p == null)
            return new Point(-1, -1);
        return p;
    }

    @Override
    public Graphics getGraphics() {
        Graphics g = super.getGraphics();

        if (g != null)
            return g;

        if (this.getBufferStrategy() == null)
            this.createBufferStrategy(buffersNeeded);

        return this.getBufferStrategy().getDrawGraphics();
    }

    protected void update() {
        screen = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void setGui(T gui) {
        this.gui = gui;
    }
}
